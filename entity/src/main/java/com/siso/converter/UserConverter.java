package com.siso.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Converter
public class UserConverter implements AttributeConverter<List<Long>,String> {

        @Override
        public String convertToDatabaseColumn(List<Long> attribute) {
            return attribute != null ? String.join(",", (CharSequence) attribute) : null;
        }

        @Override
        public List<Long> convertToEntityAttribute(String dbData) {
            return dbData != null ? Arrays.stream(dbData.split(",")).map(Long::parseLong).collect(Collectors.toList()) : null;
        }

}
