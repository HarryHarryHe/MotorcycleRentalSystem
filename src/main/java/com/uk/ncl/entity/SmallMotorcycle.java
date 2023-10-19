package com.uk.ncl.entity;

import com.uk.ncl.constant.MyConstants;

import java.util.UUID;

public class SmallMotorcycle extends Motor {
    private int batteryLevel = MyConstants.SMALL_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.SMALL_CONSUMED_PER_KM;
    private String regNum;

    public SmallMotorcycle() {
    }

    public SmallMotorcycle(int batteryLevel) {
        this.batteryLevel = batteryLevel;
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

    @Override
    public boolean isFullyCharged() {
        boolean isFully = true;
        if (batteryLevel < MyConstants.SMALL_BATTERY_LEVEL) {
            isFully = false;
        }
        return isFully;

    }

    @Override
    public int chargeMotor(int capacity) {
        int currentBattery = 0;
        //limit charging capacity per time
        if (capacity > MyConstants.SMALL_BATTERY_LEVEL) {
            capacity = MyConstants.SMALL_BATTERY_LEVEL;
        }
        currentBattery = MyConstants.SMALL_BATTERY_LEVEL - capacity;
        this.setBatteryLevel(Math.min(currentBattery, MyConstants.SMALL_BATTERY_LEVEL));
        return capacity;
    }

    @Override
    public int ride(Client client, int distance) {
        if (!canRide(client)) {
            return 0;
        }
        Motor motor = super.getRentedMotorByClient(client);
        //ride another type of motor
        if (!motor.getClass().equals(this.getClass())) {
            return 0;
        }
        int consumption = calConsumption(client, distance);
        //cal the motor battery level
        motor.setBatteryLevel(motor.getBatteryLevel() - consumption);
        return consumption;
    }

    @Override
    public int calBattery2Full() {
        return MyConstants.SMALL_BATTERY_LEVEL - this.batteryLevel;
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
