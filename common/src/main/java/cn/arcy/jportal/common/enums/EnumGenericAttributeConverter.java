package cn.arcy.jportal.common.enums;

import cn.arcy.jportal.common.utils.EnumUtil;
import cn.arcy.jportal.common.utils.ParameterizedTypeReference;
import jakarta.persistence.AttributeConverter;

import java.lang.reflect.Type;

public interface EnumGenericAttributeConverter<T extends AbstractEnum> extends AttributeConverter<T, Integer>, ParameterizedTypeReference<T> {

    default Class<T> getSupportType()
    {
        return (Class<T>) this.getType();
    }

    @Override
    default T convertToEntityAttribute(Integer dbData)
    {
        return EnumUtil.codeOf(this.getSupportType(), dbData);
    }

    @Override
    default Integer convertToDatabaseColumn(T attribute)
    {
        return attribute.getCode();
    }
}
