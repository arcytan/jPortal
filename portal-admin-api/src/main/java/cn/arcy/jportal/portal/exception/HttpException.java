package cn.arcy.jportal.portal.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class HttpException extends RuntimeException{

    @Getter
    HttpStatus status;

    public HttpException(HttpStatus status, String message)
    {
        super(message);
        this.status = status;
    }
}
