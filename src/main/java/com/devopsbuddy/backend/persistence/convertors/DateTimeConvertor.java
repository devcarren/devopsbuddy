package com.devopsbuddy.backend.persistence.convertors;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Carren.Dsouza on 22/04/2017.
 */
@Converter
public class DateTimeConvertor implements AttributeConverter<Date,Timestamp>{
    @Override
    public Timestamp convertToDatabaseColumn(Date date) {
        //return (date == null ? null : Timestamp.valueOf(date);
        return null;
    }

    @Override
    public Date convertToEntityAttribute(Timestamp timestamp) {
        return null;
    }
}
