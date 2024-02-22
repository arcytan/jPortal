package cn.arcy.jportal.autoconfigure.jpa;

import cn.arcy.jportal.common.enums.AbstractEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter()
public class EnumConverter implements AttributeConverter<Enum<?>, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Enum<?> attribute) {
        AbstractEnum abstractEnum;
        if (attribute instanceof AbstractEnum) {
            abstractEnum = (AbstractEnum) attribute;
            return abstractEnum.getCode();
        }

        return null;
    }

    @Override
    public Enum<?> convertToEntityAttribute(Integer dbData) {
        System.out.println(dbData);
        return null;
    }
}
