package cn.arcy.jportal.portal.exception;

import cn.arcy.jportal.common.utils.response.HttpResult;
import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<HttpResult<?>> authenticationException(AuthenticationException e)
    {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                HttpResult.builder()
                        .code(HttpStatus.UNAUTHORIZED.value())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<HttpResult<?>> httpMessageNotReadableException(HttpMessageNotReadableException e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                HttpResult.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message("入参格式有误！")
                        .build()
        );
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<HttpResult<?>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                HttpResult.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .build()
        );
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, BindException.class})
    public ResponseEntity<HttpResult<?>> methodArgumentNotValidException(BindException e)
    {
        HttpStatusCode status = HttpStatus.BAD_REQUEST;
        if (e instanceof MethodArgumentNotValidException methodException) {
            status = methodException.getStatusCode();
        }
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        Optional<ObjectError> error = allErrors.stream().findFirst();
        String message = error.isPresent() ? error.get().getDefaultMessage() : "未知错误！";
        /* 整理所有错误信息，并以{field:message}的格式放到response的data上 */
        Map<String, String> errorResults = new HashMap<>();
        allErrors.forEach((s) -> {
            if (!ObjectUtils.isEmpty(s.getArguments())) {
                Optional<Object> errorFirstOp = Arrays.stream(s.getArguments()).findFirst();
                if (errorFirstOp.isPresent() && (errorFirstOp.get() instanceof DefaultMessageSourceResolvable errorFirst)) {
                    errorResults.put(errorFirst.getCode(), s.getDefaultMessage());
                }
            }
        });
        return ResponseEntity
                .status(status)
                .body(
                    HttpResult.builder()
                        .code(status.value())
                        .message(message)
                        .data(errorResults)
                        .build()
        );
    }

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<HttpResult<?>> methodArgumentNotValid(ConstraintViolationException e) {
        /* 整理所有错误信息，并以{field:message}的格式放到response的data上 */
        Map<String, String> errorResults = new HashMap<>();
        e.getConstraintViolations().forEach((cv) ->{
            errorResults.put(
                    StrUtil.subAfter(cv.getPropertyPath().toString(), '.', true),
                    cv.getMessage()
            );
        });
        Optional<ConstraintViolation<?>> first = e.getConstraintViolations().stream().findFirst();
        String message = first.isPresent() ? first.get().getMessage() : "未知错误！";
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        HttpResult.builder()
                                .code(HttpStatus.BAD_REQUEST.value())
                                .message(message)
                                .data(errorResults)
                                .build()
                );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<HttpResult<?>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e)
    {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(
                        HttpResult.builder()
                                .code(HttpStatus.METHOD_NOT_ALLOWED.value())
                                .message(String.format("请求方法不支持支持[%s]", e.getMethod()))
                                .build()
                );
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<HttpResult<?>> httpException(HttpException e)
    {
        return ResponseEntity.status(e.getStatus()).body(
                HttpResult.builder()
                        .code(e.getStatus().value())
                        .message(e.getMessage())
                        .build()
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpResult<?>> runtimeException(RuntimeException e)
    {
        log.error("[{}]:{}", e.getMessage(), e.getClass());
        return ResponseEntity.internalServerError().body(
                HttpResult.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .build()
        );
    }
}
