package com.soebes.tools.cli;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.ParameterException;

/**
 * @author Karl Heinz Marbaise
 *
 */
public class DateConverter implements IStringConverter<Date> {
    public static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");


    @Override
    public Date convert(String value) {
        Date result = new Date();
        try {
            result = DEFAULT_FORMAT.parse(value);
        } catch (ParseException e) {
            throw new ParameterException("The given date is not valid.");
        }
        return result;
    }
}
