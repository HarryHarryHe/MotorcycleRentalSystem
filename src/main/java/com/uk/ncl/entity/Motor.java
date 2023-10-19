package com.uk.ncl.entity;

import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.service.impl.RentalCompanyServiceImpl;

import java.util.*;

public abstract class Motor {
    /**
     * Registration Number List (to ensure unique)
     */
    private static Set<String> usedRegNumSet = new HashSet<>();
    private RentalCompany rentalCompany = RentalCompany.getRentalCompany();

    private RentalCompanyServiceImpl rentalCompanyService = new RentalCompanyServiceImpl();
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
    public int getConsumedKM() {
        return this.consumedKM;
    }

    public void setConsumedKM(int consumedKM) {
        this.consumedKM = consumedKM;
    }

    /**
     * to check whether it is fully charged
     *
     * @return is or not
     */
    public abstract boolean isFullyCharged();

    /**
     * A method to add a given amount of charge in kWh to the battery (up to the battery's
     * capacity) and which, after execution, indicates how much charge was added.
     *
     * @param capacity unit kw.h
     * @return the charging capacity from this to max
     */
    public abstract int chargeMotor(int capacity);

    /**
     * A method to "ride" the motorcycle for a given number of kilometers, returning the
     * amount of kWh consumed during the journey.
     * @param client  Driver
     * @param distance Unit:km
     * @return returning the amount of kWh consumed during the journey.
     */
    public abstract int ride(Client client,int distance) ;
    protected Motor getRentedMotorByClient(Client client){
        return rentalCompanyService.getRentedMotorByClient(client);
    }
    protected int calConsumption(Client client,int distance){
        int consumption = 0;
        String motorType = rentalCompanyService.getMotorType(client);
        if (!MyConstants.TYPE_UNKNOWN.equals(motorType)) {
            if (MyConstants.TYPE_LARGE.equals(motorType)){
                consumption = MyConstants.LARGE_CONSUMED_PER_KM * distance;
            }
            if (MyConstants.TYPE_SMALL.equals(motorType)){
                consumption = MyConstants.SMALL_CONSUMED_PER_KM * distance;
            }
            return consumption;
        }
        return -1;
    }

    protected boolean canRide(Client client) {
        if (client == null) {
            return false;
        }
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
        if (hasMotorAndBattery(client, (List<HashMap<Motor, Client>>) largeMotorWithClientList)) {
            return true;
        }
        if (hasMotorAndBattery(client, (List<HashMap<Motor, Client>>) smallMotorWithClientList)) {
            return true;
        }
        return false;
    }

    protected boolean hasMotorAndBattery(Client client, List<HashMap<Motor, Client>> motorWithClientList) {
        for (HashMap<Motor, Client> motorClientHashMap : motorWithClientList) {
            for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                if (client.equals(entry.getValue())
                        && entry.getKey().batteryLevel > 0) {
                    return true;
                }
            }
        }
        return false;
    }
//according to the type of motor to invoke different methods

}
