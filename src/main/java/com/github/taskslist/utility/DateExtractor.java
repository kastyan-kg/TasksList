package com.github.taskslist.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kos on 20-Oct-16.
 */
public class DateExtractor {

    public Date getDateFromString(String possibleDate){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            return df.parse(possibleDate);
        } catch (ParseException ex) {
            return null;
        }
    }
}
