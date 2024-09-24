package cn.arcy.jportal.portal.handler;

import cn.arcy.jportal.common.enums.AbstractEnum;
import cn.arcy.jportal.common.utils.EnumUtil;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * 处理枚举类的json序列化和反序列化
 * 将枚举类转换为对应的code和name
 */
@JsonComponent
public class EnumJacksonHandler {

    public static class AbstractEnumJsonSerializer extends JsonSerializer<AbstractEnum> {

        @Override
        public void serialize(AbstractEnum entity, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("code", entity.getCode());
            jsonGenerator.writeStringField("name", entity.getDesc());
            jsonGenerator.writeEndObject();
        }
    }

    /*
     * 序列化默认会调用无参创建序列化工具，需要实现ContextualDeserializer来取代
     * 具体参考：
     * https://blog.csdn.net/cmland/article/details/84751452
     * https://www.cnblogs.com/kuangdaoyizhimei/p/15578482.html
     */
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AbstractEnumJsonDeserializer extends JsonDeserializer<Enum<?>> implements ContextualDeserializer {

        private JavaType javaType;

        @Override
        @SuppressWarnings("unchecked")
        public Enum<?> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
            if (ObjectUtils.isEmpty(jsonParser.getText()) || !AbstractEnum.class.isAssignableFrom(javaType.getRawClass())) {
                return null;
            }

            return (Enum<?>) EnumUtil.codeOf((Class<AbstractEnum>) javaType.getRawClass(), jsonParser.getIntValue());
        }

        @Override
        public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
            JavaType type = ctxt.getContextualType() != null
                    ? ctxt.getContextualType()
                    : property.getMember().getType();

            return new AbstractEnumJsonDeserializer(type);
        }
    }
}
