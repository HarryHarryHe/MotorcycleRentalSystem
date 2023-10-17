package com.uk.ncl.po;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Motor {
    /**
     *  Registration Number List (to ensure unique)
     */
    private static Set<String> usedRegNumSet = new HashSet<>();

//    public String regNum;
//    private String model;
//    private int speed;
//    private double consumptionRate;
//    private int batteryLevel;
    /**
     * generate the unique registration number
     * @return unique registration number
     */
    public static String genRegNum(){
        String formerStr="";
        String latterStr="";
        Random random = new Random();

        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();

        for (int i = 0; i < 2; i++) {
            char letter = (char) (random.nextInt(26) + 'A');
            stringBuilder1.append(letter);
        }
        for (int i = 0; i < 2; i++) {
            int digit = random.nextInt(10);
            stringBuilder1.append(digit);
        }
        formerStr = stringBuilder1.toString();

        for (int i = 0; i < 3; i++) {
            char letter = (char) (random.nextInt(26) + 'A');
            stringBuilder2.append(letter);
        }
        latterStr = stringBuilder2.toString();

        // ensure the registration number is unique
        String regNum = formerStr + " " + latterStr;
        while (usedRegNumSet.contains(regNum)) {
            genRegNum();
        }

        usedRegNumSet.add(regNum);
        return regNum;
    }
}
