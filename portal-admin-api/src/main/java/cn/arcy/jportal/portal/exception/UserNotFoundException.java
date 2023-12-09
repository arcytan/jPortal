package cn.arcy.jportal.portal.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends HttpException{

    public UserNotFoundException(String message)
    {
        super(HttpStatus.NOT_FOUND, message);
    }

    public UserNotFoundException()
    {
        this("找不到用户！");
    }
}
