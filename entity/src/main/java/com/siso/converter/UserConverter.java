package com.siso.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Converter
public class UserConverter implements AttributeConverter<List<String>,String> {

        @Override
        public String convertToDatabaseColumn(List<String> attribute) {
            return attribute != null ? String.join(",", attribute) : null;
        }

        @Override
        public List<String> convertToEntityAttribute(String dbData) {
            return dbData != null ? new ArrayList<>(Arrays.asList(dbData.split(","))) : null;
        }

}
