package io.github.jasonnull.dts.server.conf;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

public class JacksonModel {
    @Slf4j
    public static class LongJsonDeserializer extends JsonDeserializer<Long> {
        @Override
        public Long deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            String value = jsonParser.getText();
            try {
                return value == null ? null : Long.parseLong(value);
            } catch (NumberFormatException e) {
                log.error("解析长整形错误", e);
                return null;
            }
        }
    }

    public static class LongJsonSerializer extends JsonSerializer<Long> {
        @Override
        public void serialize(Long value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
            String text = (value == null ? null : String.valueOf(value));
            if (text != null) {
                jsonGenerator.writeString(text);
            }
        }
    }
}
