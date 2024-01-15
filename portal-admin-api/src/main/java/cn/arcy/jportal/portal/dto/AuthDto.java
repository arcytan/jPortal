package cn.arcy.jportal.portal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;

@Value
@ApiModel("用户登陆参数")
public class AuthDto {

    @ApiModelProperty(value = "用户账户/邮箱/手机号码", required = true)
    @NotEmpty(message = "用户名不能为空！")
    private String username;

    @ApiModelProperty(value = "用户密码", required = true)
    @NotEmpty(message = "用户密码不能为空！")
    private String password;
}
