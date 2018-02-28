package com.graduationdesign.expresstakeapp.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class StringConverterDate implements Converter<String,Date>{
    public Date convert(String source){
        Date date = null;
        try{
            SimpleDateFormat  format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = format.parse(source);
        }catch(Exception e){
            e.printStackTrace();
        }
            return date;
    }

}
