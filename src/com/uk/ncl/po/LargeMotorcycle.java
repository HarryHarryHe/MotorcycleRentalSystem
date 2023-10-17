package com.uk.ncl.po;

import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.factory.LargeMotorFactory;

public class LargeMotorcycle extends Motor {
    private int batteryLevel = MyConstants.LARGE_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.LARGE_CONSUMED_PER_KM;
    private int rentedMaxNum = MyConstants.RENTED_LARGE_MAX_NUM;
    private int rentedNum = 0;
    private int availableNum = rentedMaxNum - rentedNum;
    private String regNum;

    public LargeMotorcycle() {
    }
    public LargeMotorcycle(int rentedMaxNum) {
    }

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getConsumedPerKM() {
        return consumedPerKM;
    }

    public void setConsumedPerKM(int consumedPerKM) {
        this.consumedPerKM = consumedPerKM;
    }

    public int getRentedMaxNum() {
        return rentedMaxNum;
    }

    public void setRentedMaxNum(int rentedMaxNum) {
        this.rentedMaxNum = rentedMaxNum;
    }

    public int getRentedNum() {
        return rentedNum;
    }

    public void setRentedNum(int rentedNum) {
        this.rentedNum = rentedNum;
    }

    public int getAvailableNum() {
        return availableNum;
    }

    public void setAvailableNum(int availableNum) {
        this.availableNum = availableNum;
    }
}
