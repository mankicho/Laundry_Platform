package com.coders.laundry.jackson;

import com.coders.laundry.data.open.OpenLaundryData;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.CaseFormat;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.serializer.Deserializer;

@Slf4j
public class StringLowerCaseDeserializer extends JsonDeserializer<OpenLaundryData> {

  @Override
  public OpenLaundryData deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {
    ObjectCodec codec = p.getCodec();
    JsonNode treeNode = codec.readTree(p);

    OpenLaundryData openLaundryData = new OpenLaundryData();

    try {
      for (Field field : OpenLaundryData.class.getDeclaredFields()) {
        field.setAccessible(true);
        JsonNode fieldNode =
            treeNode.get(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, field.getName()));
        if (fieldNode == null) {
          continue;
        }

        String value = fieldNode.asText();
        field.set(openLaundryData, value);
      }
    } catch (IllegalAccessException e) {
      log.error(e.getMessage(), e);
    }
    return openLaundryData;
  }
}
