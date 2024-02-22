package cn.arcy.jportal.permission.enums.attribute.converters;

import cn.arcy.jportal.common.enums.EnumGenericAttributeConverter;
import cn.arcy.jportal.permission.enums.MenuType;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class MenuTypeAttributeConverter implements EnumGenericAttributeConverter<MenuType> {
}
