package cn.arcy.jportal.portal.exception;

import org.springframework.http.HttpStatus;

public class RecordNotFoundException extends HttpException{
    public RecordNotFoundException(String message) {
        super(HttpStatus.SERVICE_UNAVAILABLE, message);
    }
}
