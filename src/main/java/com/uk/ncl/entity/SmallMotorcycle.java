package com.uk.ncl.entity;

import com.uk.ncl.constant.MyConstants;

import java.util.UUID;

public class SmallMotorcycle extends Motor {
    private int batteryLevel = MyConstants.SMALL_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.SMALL_CONSUMED_PER_KM;
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
                ", regNum='" + regNum + '\'' +
                '}';
    }
}
