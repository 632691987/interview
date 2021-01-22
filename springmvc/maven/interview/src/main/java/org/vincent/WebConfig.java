package org.vincent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import static org.vincent.basic.SystemConstant.DISPLAY_DATETIME_FORMAT;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(false);
        converters.add(new ByteArrayHttpMessageConverter());
        converters.add(stringConverter);
        converters.add(new ResourceHttpMessageConverter());
        converters.add(new SourceHttpMessageConverter<>());
        converters.add(new AllEncompassingFormHttpMessageConverter());
        converters.add(jackson2Converter());
    }

    @Bean
    public MappingJackson2HttpMessageConverter jackson2Converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper());
        return converter;
    }

    @Bean
    public ObjectMapper objectMapper() {
        TimeZone timezone = TimeZone.getDefault();

        DateFormat dateFormat = new SimpleDateFormat(DISPLAY_DATETIME_FORMAT);
        dateFormat.setTimeZone(timezone);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setTimeZone(timezone);
        objectMapper.getDateFormat().setTimeZone(timezone);

        return objectMapper;
    }
}