package com.uk.ncl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tools {

    public Tools() {
    }

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static String getFormatDate(Date date){
        return dateFormat.format(date);
    }
    public static Date parseToDate(String dateString){
        Date parse;
        try {
            parse = dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return parse;
    }
    public static int getYearByDateStr(String dateString){
        String[] DateSplit = dateString.split("-");
        int bYear = Integer.parseInt(DateSplit[0]);
        String currentDate = getFormatDate(new Date());
        String[] currentSplit = currentDate.split("-");
        int cYear = Integer.parseInt(currentSplit[0]);
        return cYear-bYear;
    }
    public static int getYearByDate(Date date){
        return getYearByDateStr(getFormatDate(date));
    }

}
