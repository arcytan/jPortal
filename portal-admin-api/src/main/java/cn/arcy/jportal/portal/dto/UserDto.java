package cn.arcy.jportal.portal.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

@Value
@ApiModel("用户新增模型")
public class UserDto {

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名2不能为空！")
    @Length(min = 4, max = 16, message = "用户名长度介乎于4-16位字符！")
    private String username;

    @ApiModelProperty("昵称")
    @Nullable
    @Length(min = 2, max = 64, message = "昵称长度介乎于2-64位字符！")
    private String nickname;

    @ApiModelProperty("邮箱")
    @Nullable
    @Email(message = "请填写正确的邮箱格式！")
    private String email;

    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空！")
    @Length(min = 8, max = 32, message = "密码长度介乎于8-32位字符！")
    private String password;
}
