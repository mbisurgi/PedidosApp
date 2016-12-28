package com.designfreed.apppedidos.utils;

import com.activeandroid.serializer.TypeSerializer;

import java.util.Date;

final public class UtilDateSerializer extends TypeSerializer {
    @Override
    public Class<?> getDeserializedType() {
        return Date.class;
    }

    @Override
    public Class<?> getSerializedType() {
        return Long.class;
    }

    @Override
    public Object serialize(Object data) {
        if (data == null) {
            return null;
        }

        return ((Date) data).getTime();
    }

    @Override
    public Object deserialize(Object data) {
        if (data == null) {
            return null;
        }

        return new Date((Long) data);
    }
}
