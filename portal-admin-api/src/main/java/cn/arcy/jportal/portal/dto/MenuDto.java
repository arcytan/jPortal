package cn.arcy.jportal.portal.dto;

import cn.arcy.jportal.permission.enums.MenuType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

/**
 * DTO for {@link cn.arcy.jportal.permission.domain.entity.PermissionMenu}
 */
@Value
@ApiModel("菜单传输参数")
public class MenuDto implements Serializable {

    @ApiModelProperty("菜单名称")
    @NotEmpty(message = "菜单名称不能为空！")
    @Length(min = 1, max = 16, message = "菜单名称介乎1-16个字符！")
    String name;

    @ApiModelProperty("父级ID")
    @Min(value = 0L, message = "父菜单ID不能少于0！")
    Long parentId = 0L;

    /**
     * @see MenuType#code
     */
    @ApiModelProperty("菜单类型")
    @NotNull(message = "菜单类型获取失败！")
    MenuType type = MenuType.PAGE;

    @ApiModelProperty("是否显式菜单，默认是")
    Boolean isShow;

    @ApiModelProperty("菜单地址")
    @NotEmpty(message = "菜单地址不能为空！")
    String url;

    @ApiModelProperty("排序，号码越低排序越前")
    @Min(value = 1L, message = "排序不能少于1！")
    Integer sort = 1;

    @ApiModelProperty("是否被禁用,默认false/0")
    Boolean disabled = false;
}