package org.jeecg.common.desensitization.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import org.jeecg.common.desensitization.annotation.Sensitive;
import org.jeecg.common.desensitization.enums.SensitiveType;
import org.jeecg.common.desensitization.util.SensitiveInfoUtil;

import java.io.IOException;
import java.util.Objects;

/**
 * Jackson序列化脱敏处理器
 * 
 * @author JeecgBoot
 * @version 4.0.0
 */
public class SensitiveJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveType type;
    private String regex;
    private String replacement;

    public SensitiveJsonSerializer() {
    }

    public SensitiveJsonSerializer(SensitiveType type, String regex, String replacement) {
        this.type = type;
        this.regex = regex;
        this.replacement = replacement;
    }

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) {
            gen.writeNull();
            return;
        }

        // 根据类型进行脱敏
        String desensitizedValue;
        if (type == SensitiveType.CUSTOM) {
            desensitizedValue = SensitiveInfoUtil.desensitizeCustom(value, regex, replacement);
        } else {
            desensitizedValue = SensitiveInfoUtil.desensitize(value, type);
        }
        
        gen.writeString(desensitizedValue);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) 
            throws JsonMappingException {
        if (property != null) {
            // 获取字段上的@Sensitive注解
            Sensitive sensitive = property.getAnnotation(Sensitive.class);
            if (sensitive == null) {
                sensitive = property.getContextAnnotation(Sensitive.class);
            }
            
            if (sensitive != null) {
                // 如果字段类型是String
                if (Objects.equals(property.getType().getRawClass(), String.class)) {
                    return new SensitiveJsonSerializer(
                        sensitive.type(), 
                        sensitive.regex(), 
                        sensitive.replacement()
                    );
                }
            }
            
            return prov.findValueSerializer(property.getType(), property);
        }
        return prov.findNullValueSerializer(null);
    }
}