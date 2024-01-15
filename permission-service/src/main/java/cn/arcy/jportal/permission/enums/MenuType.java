package cn.arcy.jportal.permission.enums;

import lombok.Getter;

@Getter
public enum MenuType implements AbstractEnum<MenuType>{
    PAGE(1, "页面"),
    BUTTON(2, "按钮");

    final int code;

    final String desc;

    MenuType(int code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
