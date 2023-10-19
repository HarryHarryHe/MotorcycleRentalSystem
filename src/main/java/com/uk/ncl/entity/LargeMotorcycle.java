package com.uk.ncl.entity;

import com.uk.ncl.constant.MyConstants;

public class LargeMotorcycle extends Motor {
    private int batteryLevel = MyConstants.LARGE_BATTERY_LEVEL;
    private int consumedPerKM = MyConstants.LARGE_CONSUMED_PER_KM;
    private String regNum;

    public LargeMotorcycle() {

    }

    public LargeMotorcycle(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public LargeMotorcycle(int batteryLevel, int consumedPerKM, String regNum) {
        this.batteryLevel = batteryLevel;
        this.consumedPerKM = consumedPerKM;
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

    public String getRegNum() {
        return regNum;
    }

    public void setRegNum(String regNum) {
        this.regNum = regNum;
    }

    @Override
    public boolean isFullyCharged() {
        boolean isFully = true;
        if (batteryLevel < MyConstants.LARGE_BATTERY_LEVEL) {
            isFully = false;
        }
        return isFully;
    }

    @Override
    public int chargeMotor(int capacity) {
        int currentBattery = 0;
        //limit charging capacity per time
        if (capacity > MyConstants.LARGE_BATTERY_LEVEL) {
            capacity = MyConstants.LARGE_BATTERY_LEVEL;
        }
        currentBattery = this.batteryLevel + capacity;
        this.setBatteryLevel(Math.min(currentBattery, MyConstants.LARGE_BATTERY_LEVEL));
        return capacity;
    }

    @Override
    public int ride(Client client, int distance) {
        if (!canRide(client)) {
            return 0;
        }
        //ride another type of motor
        Motor motor = super.getRentedMotorByClient(client);
        if (!motor.getClass().equals(this.getClass())) {
            return 0;
        }
        int consumption = calConsumption(client, distance);
        //cal the motor battery level
        motor.setBatteryLevel(motor.getBatteryLevel() - consumption);
        return consumption;
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
