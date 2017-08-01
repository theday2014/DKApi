package com.danke.api.web.spring.converter;

import org.springframework.core.convert.ConversionService;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

/**
 * Created by wanghao on 2017/7/31.
 */
public class DefaultFormatterRegistrar implements FormatterRegistrar {

    public void registerFormatters(FormatterRegistry registry) {
        if (registry instanceof ConversionService) {
            ConversionService service = (ConversionService) registry;
            registry.addConverterFactory(new StringToEnumConverterFactory(
                    service));
        }
        // register custom converter.
    }
}
