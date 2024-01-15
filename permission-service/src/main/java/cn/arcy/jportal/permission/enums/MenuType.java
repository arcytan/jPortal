package cn.arcy.jportal.permission.enums;

public enum MenuType {
    PAGE(1, "页面"),
    BUTTON(2, "按钮");

    public final int code;

    public final String desc;

    MenuType(int code, String desc)
    {
        this.code = code;
        this.desc = desc;
    }
}
