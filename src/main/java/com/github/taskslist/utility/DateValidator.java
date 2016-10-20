package com.github.taskslist.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by kos on 20-Oct-16.
 */
public class DateValidator {



    public boolean checkDateFormat(String possibleDate){

        if (possibleDate == null || !possibleDate.matches("\\d{4}-[01]\\d-[0-3]\\d"))
            return false;
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(possibleDate);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }
}
