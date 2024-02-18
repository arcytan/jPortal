package cn.arcy.jportal.portal.handler;

import cn.arcy.jportal.common.enums.AbstractEnum;
import cn.arcy.jportal.permission.enums.MenuType;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.*;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

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

    /*public static class AbstractEnumJsonDeserializer extends JsonDeserializer<Object> {
        @Override
        public MenuType deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException, JacksonException {
            System.out.println("test");
            System.out.println(jsonParser.getText());
            System.out.println(jsonParser.getParsingContext().getCurrentName());
            System.out.println(jsonParser.getParsingContext().getCurrentValue());
            return null;
        }
    }*/
}
