package com.uk.ncl.entity;

import com.uk.ncl.Tools;
import com.uk.ncl.constant.MyConstants;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class Client {
    private String firstName;
    private String lastName;
    private String name;
    private String birth;
    private int age;

    private License license;
    private boolean isRenting;
    private Motor motor;

    public Client() {
    }

    public Client(String firstName, String lastName, Date birth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = Tools.getFormatDate(birth);
        this.name = firstName.substring(0, 1).toUpperCase() + firstName.substring(1) + " " +
                lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        this.age = Tools.getAgeByBirth(Tools.getFormatDate(birth));
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public License getLicense() {
        return license;
    }

    public void setLicense(License license) {
        this.license = license;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = Tools.getFormatDate(birth);
    }

    public boolean isRenting() {
        return isRenting;
    }

    public void setRenting(boolean renting) {
        isRenting = renting;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(name, client.name) && Objects.equals(birth, client.birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birth);
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", birth=" + birth +
                '}';
    }

    public static class LargeMotorcycle extends Motor {
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

    public static class RentalCompany {
        private int availableLargeNum;
        private int availableSmallNum;
        private int largeRentedNum = 0;
        private int SmallRentedNum = 0;
        private List<HashMap<Motor, Client>> motorWithClientList;

    }
}
