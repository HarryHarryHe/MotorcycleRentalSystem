package com.uk.ncl.service.impl;

import com.uk.ncl.Tools;
import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.entity.*;
import com.uk.ncl.factory.LargeMotorFactory;
import com.uk.ncl.factory.SmallMotorFactory;
import com.uk.ncl.service.RentalCompanyService;

import java.util.*;
import java.util.stream.Collectors;

public class RentalCompanyServiceImpl implements RentalCompanyService {

    public RentalCompany rentalCompany = RentalCompany.getRentalCompany();
    private LargeMotorFactory largeMotorFactory;
    private SmallMotorFactory smallMotorFactory;

    public RentalCompanyServiceImpl() {
    }

    /**
     * This method returns the number of electric motorcycle of the specified type that are available to rent
     * @param motorClazz the clazz of the subclass of Motor
     * @return the number of electric motorcycle of the specified type that are available to rent
     * @param <T>
     */
    @Override
    public <T extends Motor> int getAvailableMotorByType(Class<T> motorClazz) {
        int count = 0;
        //Calculate available quantities based on different types of motorcycles
        if (motorClazz.equals(LargeMotorcycle.class)) {
            List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
            for (HashMap<Motor, Client> motorClientHashMap : largeMotorWithClientList) {
                for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                    //This shows that this motorcycle is not bound to the user, indicating that the motorcycle is available
                    if (Objects.isNull(entry.getValue())) {
                        count++;
                    }
                }
            }
        }
        if (motorClazz.equals(SmallMotorcycle.class)) {
            List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
            for (HashMap<Motor, Client> motorClientHashMap : smallMotorWithClientList) {
                for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                    if (Objects.isNull(entry.getValue())) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    /**
     * Given a person, this method returns the electric motorcycle they are currently renting (if any)
     * @param client the specific client
     * @return The motorcycle rented by this client
     */
    @Override
    public Motor getRentedMotorByClient(Client client) {
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
        //Get the Motor corresponding to the specified Client
        List<Motor> smallCollect = smallMotorWithClientList.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> client.equals(entry.getValue()))
                .map(Map.Entry::getKey) // get Motor
                .toList();
        //it means it find a motor link to the client
        if (smallCollect.size() != 0) {
            return smallCollect.get(0);
        }
        List<Motor> largeCollect = largeMotorWithClientList.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> client.equals(entry.getValue()))
                .map(Map.Entry::getKey) // get Motor
                .toList();
        if (largeCollect.size() != 0) {
            return largeCollect.get(0);
        }
        //it doesn't find any motor by the client
        return null;
    }

    /**
     * This method returns a collection of all the electric motorcycle currently rented out
     * @return The list contains all rented motors
     */
    @Override
    public List<Motor> getRentedMotors() {
        List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        //according to the null of Client to check whether the motor has been rented, then collect into List
        List<Motor> rentedSmallList = smallMotorWithClientList.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> entry.getValue() != null)
                .map(Map.Entry::getKey)
                .toList();
        List<Motor> rentedLargeList = largeMotorWithClientList.stream()
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> entry.getValue() != null)
                .map(Map.Entry::getKey)
                .toList();
        List<Motor> totalRentedMotors = new ArrayList<>();
        //put list of small and large motors together
        totalRentedMotors.addAll(rentedSmallList);
        totalRentedMotors.addAll(rentedLargeList);
        return totalRentedMotors;
    }

    /**
     * this method determines whether
     * the person is eligible to rent a motorcycle of the specified type and, if there is a
     * motorcycle available, issues an electric motorcycle of the specified type
     * @param client specific client
     * @param license specific license
     * @param motor the type of motor
     * @return whether company can issue the motor to the client
     */
    @Override
    public boolean issueMotorToClient(Client client, License license, Motor motor) {
        client.setLicense(license);
        if (canIssue(motor, client) != 0) {
            return false;
        }
        //Rent motorcycles of this motorcycle type to the user based on the motorcycle type
        String motorType = getMotorType(motor);
        if (!MyConstants.TYPE_UNKNOWN.equals(motorType)) {
            if (MyConstants.TYPE_LARGE.equals(motorType)) {
                List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
                for (HashMap<Motor, Client> hashMap : largeMotorWithClientList) {
                    for (Map.Entry<Motor, Client> entry : hashMap.entrySet()) {
                        if (entry.getValue() == null) {
                            //issue motor, creat linking
                            entry.setValue(client);
                            int largeRentedNum = rentalCompany.getLargeRentedNum();
                            //record the rentedNum
                            rentalCompany.setLargeRentedNum(largeRentedNum + 1);
                            return true;
                        }
                    }
                }
            }
            if (MyConstants.TYPE_SMALL.equals(motorType)) {
                List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
                for (HashMap<Motor, Client> hashMap : smallMotorWithClientList) {
                    for (Map.Entry<Motor, Client> entry : hashMap.entrySet()) {
                        if (entry.getValue() == null) {
                            //issue motor, creat linking
                            entry.setValue(client);
                            int smallRentedNum = rentalCompany.getSmallRentedNum();
                            rentalCompany.setSmallRentedNum(smallRentedNum + 1);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * Get the type by motor
     *
     * @param motor motor instance
     * @return string of the motor type
     */
    private String getMotorType(Motor motor) {
        if (motor.getClass().equals(LargeMotorcycle.class)) {
            return MyConstants.TYPE_LARGE;
        }
        if (motor.getClass().equals(SmallMotorcycle.class)) {
            return MyConstants.TYPE_SMALL;
        }
        return MyConstants.TYPE_UNKNOWN;
    }

    //get the string type of motor by client
    public String getMotorType(Client client) {
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        for (HashMap<Motor, Client> motorClientHashMap : largeMotorWithClientList) {
            for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                if (client.equals(entry.getValue())) {
                    return entry.getKey().getClass().equals(LargeMotorcycle.class)
                            ? MyConstants.TYPE_LARGE : MyConstants.TYPE_SMALL;
                }
            }
        }
        return MyConstants.TYPE_UNKNOWN;
    }

    /**
     * The following rules determine the meaning and functionality of the ride method (and of the
     * method to get the capacity of a motorcycle's battery):
     *  A motorcycle cannot be ridden if it is not currently rented.
     *  A motorcycle cannot be ridden if it has 0 or fewer kWh of charge in its battery
     * @param motor
     * @param client
     * @return only return 0 that client could rent this motor
     */
    @Override
    public int canIssue(Motor motor, Client client) {
        if (motor == null || client == null) {
            //invalid param
            return -2;
        }

        License license = client.getLicense();
        boolean isFormal = license.getIsFormal();
        if (!isFormal) {
            //is not formal
            return 2;
        }
        int age = client.getAge();
        Date issueDate = license.getIssueDate();
        int issuedYear = Tools.getYearByDate(issueDate);

        //rent largeMotor: whether it outnumber or equal the figure of max num
        //and check the age and issuedYear
        if (motor.getClass().equals(LargeMotorcycle.class)) {
            if ((age < MyConstants.RENT_LARGE_LIMIT_AGE || issuedYear < MyConstants.RENT_LARGE_LICENSE_LIMIT_YEAR)) {
                //proof fail
                return 3;
            }
            List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
            // if the client had rented or no available motors return 1
            if (beOccupied(client, largeMotorWithClientList)) return 1;
            if (rentalCompany.getLargeRentedNum() >= MyConstants.RENTED_LARGE_MAX_NUM) {
                    //proof fail
                    return 3;
            }

        } else if (motor.getClass().equals(SmallMotorcycle.class)) {
            if ((age < MyConstants.RENT_SMALL_LIMIT_AGE || issuedYear < MyConstants.RENT_SMALL_LICENSE_LIMIT_YEAR)) {
                return 3;
            }
            List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
            // if the client had rented or no available motors return 1
            if (beOccupied(client, largeMotorWithClientList)) return 1;
            if (rentalCompany.getSmallRentedNum() >= MyConstants.RENTED_SMALL_MAX_NUM) {
                //proof fail)
                return 3;
            }
        }
        //could tent
        return 0;
    }

    /**
     * to check whether client has occupied a motor or this motor has been occupied
     * @param client
     * @param motorWithClientList
     * @return whether motor don't have the owner && client don't own other motors
     */
    private boolean beOccupied(Client client, List<HashMap<Motor, Client>> motorWithClientList) {
        boolean beOccupied = true;
        for (HashMap<Motor, Client> motorClientHashMap : motorWithClientList) {
            for (Map.Entry<Motor, Client> entry : motorClientHashMap.entrySet()) {
                //motor has been rented to others or client has rented
                if (entry.getValue() == null) {
                    //available
                    beOccupied = false;
                }
                //motor has been rented to this client, return directly
                if (entry.getValue() == client) {
                    //had been rented
                    beOccupied = true;
                    return beOccupied;
                }
            }
        }
        return beOccupied;
    }

    /**
     * This method terminates the given person's rental contract, effectively making the
     * electric motorcycle available for rent by another person.
     * @param client
     * @return the battery charge level required to reach full capacity
     */
    @Override
    public int terminateRental(Client client) {
        Motor motor = null;
        int batteryLevel2Full = 0;
        Motor motorcycle = getRentedMotorByClient(client);
        //this client didn't rent any motors
        if (motorcycle == null) {
            return -1;
        }
        //if rent largeMotor
        if (motorcycle.getClass().equals(LargeMotorcycle.class)) {
            List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
            for (HashMap<Motor, Client> hashMap : largeMotorWithClientList) {
                for (Map.Entry<Motor, Client> entry : hashMap.entrySet()) {
                    //find this client in map
                    if (entry.getValue() == client) {
                        //delete renting record
                        entry.setValue(null);
                        int largeRentedNum = rentalCompany.getLargeRentedNum();
                        //Reduce the number of rented cars by 1
                        rentalCompany.setLargeRentedNum(largeRentedNum - 1);
                        motor = (LargeMotorcycle) entry.getKey();
                        //Calculate the amount of energy required for a full charge
                        batteryLevel2Full = motor.calBattery2Full();
                        //recharge the battery and then return
                        motor.setBatteryLevel(MyConstants.LARGE_BATTERY_LEVEL);
                        return batteryLevel2Full;
                    }
                }
            }
        }
        //if rent smallMotor
        if (motorcycle.getClass().equals(SmallMotorcycle.class)){
            List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
            for (HashMap<Motor, Client> hashMap : smallMotorWithClientList) {
                for (Map.Entry<Motor, Client> entry : hashMap.entrySet()) {
                    if (entry.getValue() == client) {
                        //delete record
                        entry.setValue(null);
                        int smallRentedNum = rentalCompany.getSmallRentedNum();
                        rentalCompany.setSmallRentedNum(smallRentedNum - 1);
                        motor = (SmallMotorcycle) entry.getKey();
                        batteryLevel2Full = motor.calBattery2Full();
                        //recharge the battery and then return
                        motor.setBatteryLevel(MyConstants.SMALL_BATTERY_LEVEL);
                        return batteryLevel2Full;
                    }
                }
            }
        }
        //didn't find this client
        return -1;
    }
}
