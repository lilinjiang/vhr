package com.jiang.vhr.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author lilinjiang
 * 字符串LONG 转data
 */
@Component
public class DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        try {
            return new Date(Long.parseLong(source));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
