package com.ssts.ssts.auth.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class GuestObjectSerializer extends StdSerializer<GuestObject> {

    public GuestObjectSerializer() {
        this(null);
    }

    public GuestObjectSerializer(Class<GuestObject> t) {
        super(t);
    }

    @Override
    public void serialize(GuestObject value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("email", value.getEmail());
        gen.writeEndObject();
    }
}
