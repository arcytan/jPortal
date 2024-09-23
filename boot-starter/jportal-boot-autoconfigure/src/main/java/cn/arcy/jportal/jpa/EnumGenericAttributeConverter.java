package cn.arcy.jportal.jpa;

import cn.arcy.jportal.common.enums.AbstractEnum;
import cn.arcy.jportal.common.utils.EnumUtil;
import cn.arcy.jportal.common.utils.ParameterizedTypeReference;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.lang.reflect.Type;

@Converter
public interface EnumGenericAttributeConverter<T extends AbstractEnum> extends AttributeConverter<T, Integer>, ParameterizedTypeReference<T> {

    @SuppressWarnings("unchecked")
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
