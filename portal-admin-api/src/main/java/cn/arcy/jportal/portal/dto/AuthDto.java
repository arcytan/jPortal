package cn.arcy.jportal.portal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("用户登陆参数")
public class AuthDto {

    @ApiModelProperty(value = "用户账户/邮箱/手机号码", required = true)
    String username;

    @ApiModelProperty(value = "用户密码", required = true)
    String password;
}
