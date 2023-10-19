package com.uk.ncl.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RentalCompany {

    //hungry singleton
    private static final RentalCompany rentalCompany = new RentalCompany();

    private int largeRentedNum = 0;
    private int smallRentedNum = 0;
    private HashMap<Motor, Client> largeMotorWithClientMap = new HashMap<>();
    private List<HashMap<Motor, Client>> largeMotorWithClientList = new ArrayList<>();

    private HashMap<Motor, Client> smallMotorWithClientMap = new HashMap<>();

    /**
     * Records the association between motorcycles and users
     */
    private List<HashMap<Motor, Client>> smallMotorWithClientList = new ArrayList<>();

    private RentalCompany(){}

    //hungry singleton
    public static RentalCompany getRentalCompany(){
        return rentalCompany;
    }

    public int getLargeRentedNum() {
        return largeRentedNum;
    }

    public void setLargeRentedNum(int largeRentedNum) {
        this.largeRentedNum = largeRentedNum;
    }

    public int getSmallRentedNum() {
        return smallRentedNum;
    }

    public void setSmallRentedNum(int smallRentedNum) {
        this.smallRentedNum = smallRentedNum;
    }

    /**
     * Get Motorcycle & Customer Relations
     * @return
     */
    public HashMap<Motor, Client> getLargeMotorWithClientMap() {
        return largeMotorWithClientMap;
    }

    /**
     * Set up motorcycle and customer relationships
     * @param largeMotorWithClientMap
     */
    public void setLargeMotorWithClientMap(HashMap<Motor, Client> largeMotorWithClientMap) {
        this.largeMotorWithClientMap = largeMotorWithClientMap;
    }

    /**
     * Get the list of binding relationships between motorcycles and users
     * @return
     */
    public List<HashMap<Motor, Client>> getLargeMotorWithClientList() {
        return largeMotorWithClientList;
    }

    /**
     * Bind motorcycles to users and set them as a collection
     * @param largeMotorWithClientList
     */
    public void setLargeMotorWithClientList(List<HashMap<Motor, Client>> largeMotorWithClientList) {
        this.largeMotorWithClientList = largeMotorWithClientList;
    }

    public HashMap<Motor, Client> getSmallMotorWithClientMap() {
        return smallMotorWithClientMap;
    }

    public void setSmallMotorWithClientMap(HashMap<Motor, Client> smallMotorWithClientMap) {
        this.smallMotorWithClientMap = smallMotorWithClientMap;
    }

    public List<HashMap<Motor, Client>> getSmallMotorWithClientList() {
        return smallMotorWithClientList;
    }

    public void setSmallMotorWithClientList(List<HashMap<Motor, Client>> smallMotorWithClientList) {
        this.smallMotorWithClientList = smallMotorWithClientList;
    }

}
