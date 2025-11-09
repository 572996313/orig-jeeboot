package org.jeecg.common.exception;

import org.jeecg.common.api.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.util.stream.Collectors;

/**
 * 全局异常处理器
 *
 * @author JeecgBoot
 * @version 4.0.0
 * @since 2025-01-08
 */
@RestControllerAdvice
public class JeecgBootExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(JeecgBootExceptionHandler.class);

    /**
     * 处理自定义业务异常
     */
    @ExceptionHandler(JeecgBootException.class)
    @ResponseStatus(HttpStatus.OK)
    public Result<?> handleJeecgBootException(JeecgBootException e, HttpServletRequest request) {
        log.error("业务异常 - URI: {}, Message: {}", request.getRequestURI(), e.getMessage());
        return Result.error(e.getMessage());
    }

    /**
     * 处理参数校验异常（@Valid）
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining("; "));
        log.error("参数校验失败 - URI: {}, Message: {}", request.getRequestURI(), message);
        return Result.error("参数校验失败: " + message);
    }

    /**
     * 处理参数绑定异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handleBindException(BindException e, HttpServletRequest request) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));
        log.error("参数绑定失败 - URI: {}, Message: {}", request.getRequestURI(), message);
        return Result.error("参数绑定失败: " + message);
    }

    /**
     * 处理约束违反异常
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        String message = e.getConstraintViolations().stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining("; "));
        log.error("约束违反 - URI: {}, Message: {}", request.getRequestURI(), message);
        return Result.error("参数校验失败: " + message);
    }

    /**
     * 处理参数类型不匹配异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<?> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.error("参数类型不匹配 - URI: {}, Parameter: {}, Message: {}", 
                request.getRequestURI(), e.getName(), e.getMessage());
        return Result.error("参数类型不匹配: " + e.getName());
    }

    /**
     * 处理404异常
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<?> handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        log.error("接口不存在 - URI: {}", request.getRequestURI());
        return Result.error(404, "接口不存在: " + request.getRequestURI());
    }

    /**
     * 处理请求方法不支持异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.error("请求方法不支持 - URI: {}, Method: {}", request.getRequestURI(), request.getMethod());
        return Result.error("请求方法不支持: " + request.getMethod());
    }

    /**
     * 处理SQL异常
     */
    @ExceptionHandler(SQLException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleSQLException(SQLException e, HttpServletRequest request) {
        log.error("SQL异常 - URI: {}, SQLState: {}, ErrorCode: {}, Message: {}",
                request.getRequestURI(), e.getSQLState(), e.getErrorCode(), e.getMessage());
        return Result.error("数据库操作失败");
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleNullPointerException(NullPointerException e, HttpServletRequest request) {
        log.error("空指针异常 - URI: {}", request.getRequestURI(), e);
        return Result.error("系统内部错误，请联系管理员");
    }

    /**
     * 处理所有未捕获的异常
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<?> handleException(Exception e, HttpServletRequest request) {
        log.error("未知异常 - URI: {}", request.getRequestURI(), e);
        return Result.error("系统异常，请联系管理员");
    }
}