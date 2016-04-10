package by.couriers.operator.model;

import org.mongodb.morphia.converters.SimpleValueConverter;
import org.mongodb.morphia.converters.TypeConverter;
import org.mongodb.morphia.mapping.MappedField;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class LocalDateTimeConverter extends TypeConverter implements SimpleValueConverter {

    public LocalDateTimeConverter() {
        super(LocalDateTime.class);
    }

    @Override
    public Object decode(Class<?> targetClass, Object fromDBObject, MappedField optionalExtraInfo) {
        if (fromDBObject == null) {
            return null;
        }

        if (fromDBObject instanceof Date) {
            return ((Date) fromDBObject).toInstant().atZone(ZoneOffset.systemDefault()).toLocalDateTime();
        }

        if (fromDBObject instanceof LocalDateTime) {
            return fromDBObject;
        }

        throw new IllegalArgumentException(String.format("Cannot decode object of class: %s", fromDBObject.getClass().getName()));
    }

    @Override
    public Object encode(Object value, MappedField optionalExtraInfo) {
        if (value == null) {
            return null;
        }

        if (value instanceof Date) {
            return value;
        }

        if (value instanceof LocalDateTime) {
            ZonedDateTime zoned = ((LocalDateTime) value).atZone(ZoneOffset.systemDefault());
            return Date.from(zoned.toInstant());
        }

        throw new IllegalArgumentException(String.format("Cannot encode object of class: %s", value.getClass().getName()));
    }
}