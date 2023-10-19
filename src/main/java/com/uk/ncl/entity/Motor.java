package com.uk.ncl.entity;

import com.uk.ncl.constant.MyConstants;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public abstract class Motor {
    /**
     * Registration Number List (to ensure unique)
     */
    private static Set<String> usedRegNumSet = new HashSet<>();
    private RentalCompany rentalCompany = RentalCompany.getRentalCompany();
    private int batteryLevel = MyConstants.LARGE_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.LARGE_CONSUMED_PER_KM;
    private int consumedKM = 0;

//    public String regNum;
//    private String model;
//    private int speed;
//    private double consumptionRate;
//    private int batteryLevel;

    /**
     * generate the unique registration number
     *
     * @return unique registration number
     */
    public String genRegNum() {
        String formerStr = "";
        String latterStr = "";
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

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public abstract int getConsumedPerKM();

    public abstract void setConsumedPerKM(int consumedPerKM);

    //get the driving distance of the motor
    public int getConsumedKM(){
        return this.consumedKM;
    }

    public void setConsumedKM(int consumedKM) {
        this.consumedKM = consumedKM;
    }

    /**
     * to check whether it is fully charged
     * @return is or not
     */
    public boolean isFullyCharged(){
        boolean isFully = true;
        if (this.getClass().equals(LargeMotorcycle.class)){
            if (this.batteryLevel < MyConstants.LARGE_BATTERY_LEVEL) {
                isFully = false;
            }
        }
        if (this.getClass().equals(SmallMotorcycle.class)){
            if (this.batteryLevel < MyConstants.SMALL_BATTERY_LEVEL) {
                isFully = false;
            }
        }
        return isFully;
    }
    /**
     * A method to add a given amount of charge in kWh to the battery (up to the battery's
     * capacity) and which, after execution, indicates how much charge was added.
     * @param capacity unit kw.h
     * @return the charging capacity from this to max
     */
    public int chargeMotor(int capacity){
        int gap2Max = 0;
        if (this.getClass().equals(LargeMotorcycle.class)){
            if (capacity<MyConstants.LARGE_BATTERY_LEVEL){
                gap2Max = MyConstants.LARGE_BATTERY_LEVEL - capacity;
            }
        }
        if (this.getClass().equals(SmallMotorcycle.class)){
            if (capacity<MyConstants.SMALL_BATTERY_LEVEL){
                gap2Max = MyConstants.SMALL_BATTERY_LEVEL - capacity;
            }
        }
        return gap2Max;
    }

    public int ride(Client client){
        canRide(client);
        return 2;
    }

    private boolean canRide(Client client) {

        return false;
    }
//according to the type of motor to invoke different methods

}
