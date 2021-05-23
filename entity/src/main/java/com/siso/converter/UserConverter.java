package com.siso.converter;

import org.apache.commons.lang3.StringUtils;

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
            return attribute != null ? attribute.stream().map(Object::toString).collect(Collectors.joining(",")) : null;
        }

        @Override
        public List<Long> convertToEntityAttribute(String dbData) {
            System.out.println(Arrays.stream(dbData.split(",")).map(Long::parseLong).collect(Collectors.toList()));
            return dbData != null ? Arrays.stream(dbData.split(",")).map(Long::parseLong).collect(Collectors.toList()) : null;
        }

}
