package cn.arcy.jportal.portal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("用户登陆返回参数")
@Data
public class AuthVo {

    @ApiModelProperty("用户名")
    String username;

    @ApiModelProperty("用户昵称")
    String nickname;

    @ApiModelProperty("用户邮箱")
    String email;

    @ApiModelProperty("用户是否不可用")
    Boolean disabled;

    @ApiModelProperty("创建时间")
    LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    LocalDateTime updatedAt;

    @ApiModelProperty("用户TOKEN")
    String token;

}
