package com.dorm.ex.handler;

import com.dorm.enums.CodeEnum;
import com.dorm.ex.BizEx;
import com.dorm.res.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class ExHandler {

    @ExceptionHandler(BindException.class)
    public Result<String> handleEx(BindException e) {
        log.error("参数绑定异常", e);
        List<FieldError> fieldErrors = e.getFieldErrors();
        return Result.buildFailure(spliceErrMsg(fieldErrors));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleEx(MethodArgumentNotValidException e) {
        log.error("入参校验失败", e);
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return Result.buildFailure(spliceErrMsg(fieldErrors));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleEx(ConstraintViolationException e) {
        log.error("违反约束", e);
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String str = constraintViolations.stream()
                .map(v -> "属性:" + v.getPropertyPath() + ", 传来的值是:" + v.getInvalidValue() + ", 校验不通过, 原因:" + v.getMessage())
                .collect(Collectors.joining(";"));
        return Result.buildFailure(str);
    }

    @ExceptionHandler(BizEx.class)
    public Result<String> handleEx(BizEx e) {
        log.error("业务异常", e);
        return Result.buildFailure(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result<String> handleEx(Exception e) {
        log.error("处理未知异常", e);
        return Result.buildFailure(CodeEnum.SYS_ERR);
    }

    private String spliceErrMsg(List<FieldError> fieldErrors) {
        return fieldErrors.stream()
                .map(v -> "字段:" + v.getField() + ", 传来的值是:" + v.getRejectedValue() + ", 校验不通过, 原因:" + v.getDefaultMessage())
                .collect(Collectors.joining(";"));
    }
}
