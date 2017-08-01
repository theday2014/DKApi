package com.danke.api.web.spring.converter;

import common.domain.context.Convertible;
import common.domain.context.ConvertibleContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

/**
 * Created by wanghao on 2017/7/31.
 */
public final class StringToEnumConverterFactory implements ConverterFactory<String, Enum<?>> {

    private final ConversionService conversionService;

    public StringToEnumConverterFactory(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    public <T extends Enum<?>> Converter<String, T> getConverter(
            Class<T> targetType) {
        return new StringToEnum(conversionService, targetType);
    }

    static class StringToEnum<T extends Enum<T>> implements Converter<String, T> {

        private final ConversionService conversionService;
        private final Class<T> enumType;

        StringToEnum(ConversionService conversionService, Class<T> enumType) {
            this.conversionService = conversionService;
            this.enumType = enumType;
        }

        public T convert(String source) {
            if (enumType.isAnnotationPresent(Convertible.class)) {
                ConvertibleContext<T, Object> convertibleContext = ConvertibleContext.build(enumType);

                Object fromValue;
                if (source == null || source.length() == 0) {
                    // It's an empty enum identifier: reset the enum value to
                    // null.
                    fromValue = null;
                } else {
                    fromValue = conversionService.convert(source, convertibleContext.getValueType());
                }

                try {
                    return enumType.cast(convertibleContext.of(fromValue));
                } catch (Exception e) {
                    throw new IllegalArgumentException(e);
                }
            } else if (source == null || source.length() == 0) {
                return null;
            }
            // fall back to normal conversion.
            return Enum.valueOf(this.enumType, source.trim());
        }
    }
}
