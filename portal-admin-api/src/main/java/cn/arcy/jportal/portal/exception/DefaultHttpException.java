package cn.arcy.jportal.portal.exception;

import org.springframework.http.HttpStatus;

public class DefaultHttpException extends HttpException{
    public DefaultHttpException(String message) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message);
    }
}
