package com.example.demo.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends StdSerializer<LocalDateTime> {
    private static String LOCAL_DATE_PATTERN = "M/dd/yyyy h:mm:ss.SSSSSSSSS a";
    private static final long serialVersionUID = 1L;

    public LocalDateSerializer(){
        super(LocalDateTime.class);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.format(DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN)));
    }
}
