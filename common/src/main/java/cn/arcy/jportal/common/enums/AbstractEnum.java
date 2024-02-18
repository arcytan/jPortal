package cn.arcy.jportal.common.enums;

@JsonDeserializer
public interface AbstractEnum {
    public int getCode() ;

    public String getDesc();
}
