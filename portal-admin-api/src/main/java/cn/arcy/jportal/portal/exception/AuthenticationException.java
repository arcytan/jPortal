package cn.arcy.jportal.portal.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends HttpException{
    public AuthenticationException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
