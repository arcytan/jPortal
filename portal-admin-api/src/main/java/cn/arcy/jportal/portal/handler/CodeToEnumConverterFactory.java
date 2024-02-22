package cn.arcy.jportal.portal.handler;

import cn.arcy.jportal.common.enums.AbstractEnum;
import cn.arcy.jportal.common.utils.EnumUtil;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * 将获取的code
 */
public class CodeToEnumConverterFactory implements ConverterFactory<Integer, AbstractEnum> {
    @Override
    public <T extends AbstractEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new CodeToEnum(targetType);
    }

    private static class CodeToEnum<T extends Enum<T> & AbstractEnum> implements Converter<Integer, T> {

        private final Class<T> enumType;

        CodeToEnum(Class<T> enumType) {
            this.enumType = enumType;
        }

        @Override
        public T convert(Integer source) {
            if (source.compareTo(0) <= 0) {
                return null;
            }

            return EnumUtil.codeOf(this.enumType, source);
        }
    }
}
