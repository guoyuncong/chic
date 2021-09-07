package com.chic.core.handle;

import com.chic.core.base.enums.ResultCode;
import com.chic.core.base.model.dto.ValidErrorDataDTO;
import com.chic.core.base.model.vo.R;
import com.chic.core.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ElementKind;
import javax.validation.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 全局处理异常
 *
 * @author: yc
 * @date: 2021-05-25
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常
     *
     * @param bizException 捕获异常
     * @return R
     */
    @ExceptionHandler({BizException.class})
    public R bizExceptionHandler(BizException bizException) {
        if (bizException.getData() == null) {
            return R.of(bizException.getBizResponse());
        } else {
            return R.of(bizException.getBizResponse(), bizException.getData());
        }
    }

    /**
     * 异常
     *
     * @param ex BindException.class
     * @return R
     */
    @ExceptionHandler(value = BindException.class)
    public R bindException(BindException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ValidErrorDataDTO> validErrorDataDTOS = parseBindingResult(bindingResult);
        return R.of(ResultCode.USER_REQUEST_PARAM_ERROR, validErrorDataDTOS);
    }

    /**
     * 参数异常
     *
     * @param ex MethodArgumentNotValidException.class
     * @return R
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public R methodArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ValidErrorDataDTO> validErrorDataDTOS = parseBindingResult(bindingResult);
        return R.of(ResultCode.USER_REQUEST_PARAM_ERROR, validErrorDataDTOS);
    }

    /**
     * 参数异常
     *
     * @param ex ConstraintViolationException.class
     * @return R
     */
    @ExceptionHandler(value = ConstraintViolationException.class)
    public R constraintViolationException(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        List<ValidErrorDataDTO> validErrorDataVOS = handleViolations4validErrorData(violations);
        return R.of(ResultCode.USER_REQUEST_PARAM_ERROR, validErrorDataVOS);
    }

//    /**
//     * 服务器内部异常
//     *
//     * @param ex 服务器内部异常
//     * @return R
//     */
//    @ExceptionHandler(Exception.class)
//    public R exceptionHandler(Exception ex) {
//        return R.of(ResultCode.BIZ_SYSTEM_EXECUTE_ERROR);
//    }

    /**
     * 解析异常信息
     *
     * @param bindingResult 異常信息
     * @return List<ValidErrorDataDTO>
     */
    private List<ValidErrorDataDTO> parseBindingResult(BindingResult bindingResult) {
        return bindingResult.getFieldErrors()
                .stream()
                .map(error ->
                        ValidErrorDataDTO.builder()
                                .name(error.getField())
                                .value(error.getRejectedValue())
                                .message(error.getDefaultMessage())
                                .build()
                )
                .collect(Collectors.toList());
    }

    /**
     * 解析异常信息
     *
     * @param violations 異常信息
     * @return List<ValidErrorDataDTO>
     */
    private List<ValidErrorDataDTO> handleViolations4validErrorData(Set<ConstraintViolation<?>> violations) {
        return violations.stream()
                .map(violation -> {
                    ValidErrorDataDTO validErrorDataDTO = new ValidErrorDataDTO();
                    Path propertyPath = violation.getPropertyPath();
                    propertyPath.forEach(node -> {
                        if (ElementKind.PARAMETER.equals(node.getKind()) || ElementKind.PROPERTY.equals(node.getKind())) {
                            validErrorDataDTO.setName(node.getName());
                        }
                    });
                    validErrorDataDTO.setValue(violation.getInvalidValue());
                    validErrorDataDTO.setMessage(violation.getMessage());
                    return validErrorDataDTO;
                })
                .collect(Collectors.toList());

    }
}
