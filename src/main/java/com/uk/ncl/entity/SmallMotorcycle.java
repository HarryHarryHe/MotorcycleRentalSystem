package com.uk.ncl.entity;

import com.uk.ncl.constant.MyConstants;

import java.util.UUID;

public class SmallMotorcycle extends Motor {
    private int batteryLevel = MyConstants.SMALL_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.SMALL_CONSUMED_PER_KM;
    private int rentedMaxNum = MyConstants.RENTED_SMALL_MAX_NUM;
    private int rentedNum = 0;
    private int availableNum = rentedMaxNum - rentedNum;

    private String regNum;
    public SmallMotorcycle() {
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

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    @Override
    public String toString() {
        return "SmallMotorcycle{" +
                "batteryLevel=" + batteryLevel +
                ", consumedPerKM=" + consumedPerKM +
                ", rentedMaxNum=" + rentedMaxNum +
                ", rentedNum=" + rentedNum +
                ", availableNum=" + availableNum +
                ", regNum='" + regNum + '\'' +
                '}';
    }
}
