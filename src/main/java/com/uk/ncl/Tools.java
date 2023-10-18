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
    public static int getAgeByBirth(String dateString){
        String[] DateSplit = dateString.split("-");
        int bYear = Integer.parseInt(DateSplit[0]);
        String currentDate = getFormatDate(new Date());
        String[] currentSplit = currentDate.split("-");
        int cYear = Integer.parseInt(currentSplit[0]);
        return cYear-bYear;
    }


    public static void main(String[] args) {
        System.out.println(getAgeByBirth("1999-04-28"));
    }
}
