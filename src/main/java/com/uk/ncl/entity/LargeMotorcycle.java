package com.uk.ncl.entity;

import com.uk.ncl.constant.MyConstants;

public class LargeMotorcycle extends Motor {
    private int batteryLevel = MyConstants.LARGE_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.LARGE_CONSUMED_PER_KM;
    private String regNum;
    public LargeMotorcycle() {

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
        return "LargeMotorcycle{" +
                "batteryLevel=" + batteryLevel +
                ", consumedPerKM=" + consumedPerKM +
                ", regNum='" + regNum + '\'' +
                '}';
    }
}
