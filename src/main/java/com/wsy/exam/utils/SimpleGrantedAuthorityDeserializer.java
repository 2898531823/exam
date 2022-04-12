package com.wsy.exam.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.io.IOException;

/**
 * @className: com.wsy.exam.utils-> SimpleGrantedAuthorityDeserializer
 * @description:
 * @author: wsy
 * @createDate: 2022-04-10 19:55
 * @version: 1.0
 */
public class SimpleGrantedAuthorityDeserializer extends StdDeserializer<SimpleGrantedAuthority> {

    public SimpleGrantedAuthorityDeserializer() {
        super(SimpleGrantedAuthority.class);
    }

    @Override
    public SimpleGrantedAuthority deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode tree = p.getCodec().readTree(p);
        return new SimpleGrantedAuthority(tree.get("authority").textValue());
    }
}
