package com.uk.ncl.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RentalCompany {

    //hungry singleton
    private static final RentalCompany rentalCompany = new RentalCompany();
    private Client client;

    private int availableLargeNum;
    private int availableSmallNum;
    private int largeRentedNum = 0;
    private int SmallRentedNum = 0;
    private HashMap<Motor, Client> largeMotorWithClientMap = new HashMap<>();
    private List<HashMap<Motor, Client>> largeMotorWithClientList = new ArrayList<>();

    private HashMap<Motor, Client> smallMotorWithClientMap = new HashMap<>();
    private List<HashMap<Motor, Client>> smallMotorWithClientList = new ArrayList<>();

    private RentalCompany(){}

    //hungry singleton
    public static RentalCompany getRentalCompany(){
        return rentalCompany;
    }

    /**
     * 获取摩托车与客户关系
     * @return
     */
    public HashMap<Motor, Client> getLargeMotorWithClientMap() {
        return largeMotorWithClientMap;
    }

    /**
     * 设置摩托车与客户关系
     * @param largeMotorWithClientMap
     */
    public void setLargeMotorWithClientMap(HashMap<Motor, Client> largeMotorWithClientMap) {
        this.largeMotorWithClientMap = largeMotorWithClientMap;
    }

    /**
     * 获取摩托车与用户的绑定关系列表
     * @return
     */
    public List<HashMap<Motor, Client>> getLargeMotorWithClientList() {
        return largeMotorWithClientList;
    }

    /**
     * 将摩托车与用户绑定并设成集合
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
