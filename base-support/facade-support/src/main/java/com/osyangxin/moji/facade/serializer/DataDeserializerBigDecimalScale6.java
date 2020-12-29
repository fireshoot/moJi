package com.osyangxin.moji.facade.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public class DataDeserializerBigDecimalScale6 extends JsonDeserializer<BigDecimal> {
    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        if (Objects.isNull(p.getDecimalValue())) {
            return null;
        } else {
            return p.getDecimalValue().setScale(6, RoundingMode.DOWN);
        }
    }
}
