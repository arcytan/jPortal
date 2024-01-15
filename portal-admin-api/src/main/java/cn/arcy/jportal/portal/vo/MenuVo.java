package cn.arcy.jportal.portal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@ApiModel("用户菜单返回参数")
@Data
public class MenuVo {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("上级ID")
    private Long parentId;

    @ApiModelProperty("不可用")
    private boolean disabled;

    @ApiModelProperty("创建时间")
    private LocalDateTime createAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;
}
