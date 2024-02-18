package cn.arcy.jportal.portal.vo;

import cn.arcy.jportal.permission.enums.MenuType;
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

    @ApiModelProperty("等级")
    private Byte level;

    @ApiModelProperty("菜单类型")
    private MenuType type;

    @ApiModelProperty("不可用")
    private boolean disabled;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty("更新时间")
    private LocalDateTime updatedAt;
}
