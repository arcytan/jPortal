package cn.arcy.jportal.permission.enums;

import cn.arcy.jportal.common.enums.AbstractEnum;
import lombok.Getter;

@Getter
public enum MenuType implements AbstractEnum {
    PAGE(1, "页面"),
    BUTTON(2, "按钮");

    public final int code;

    public final String desc;

    MenuType(int code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }

    /*@JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static MenuType forCode(Object code)
    {
        return EnumUtil.codeOf(MenuType.class, Integer.parseInt(code.toString()));
    }*/
}
