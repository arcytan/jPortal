package cn.arcy.jportal.portal.exception;

import cn.arcy.jportal.portal.util.HttpResult;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<HttpResult<?>> runtimeException(RuntimeException e)
    {
        return ResponseEntity.internalServerError().body(
                HttpResult.builder()
                        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                        .message(e.getMessage())
                        .build()
        );
    }

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

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<HttpResult<?>> methodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e)
    {
        return ResponseEntity.internalServerError().body(
                HttpResult.builder()
                        .code(HttpStatus.BAD_REQUEST.value())
                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .build()
        );
    }

    //HttpMediaTypeNotAcceptableException
}
