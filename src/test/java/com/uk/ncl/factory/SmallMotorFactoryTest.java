package com.uk.ncl.factory;

import com.uk.ncl.entity.Client;
import com.uk.ncl.entity.Motor;
import com.uk.ncl.entity.RentalCompany;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class SmallMotorFactoryTest{
    RentalCompany rentalCompany = RentalCompany.getRentalCompany();
    MotorFactory motorFactory = new SmallMotorFactory();
    boolean isOk = motorFactory.create();
    @Test
    public void testCreate() {
        List<Motor> motorcycles = motorFactory.getMotorcycles();
        List<HashMap<Motor, Client>> smallMotorWithClientList = rentalCompany.getSmallMotorWithClientList();
        for (Motor motorcycle : motorcycles) {
            System.out.println(motorcycle);
        }
        for (Iterator<HashMap<Motor, Client>> iterator = smallMotorWithClientList.iterator();
             iterator.hasNext(); ) {
            HashMap<Motor, Client> next = iterator.next();
            for (Map.Entry<Motor, Client> entry : next.entrySet()) {
                Client client = entry.getValue();
                System.out.println(entry.getKey() + ": " + (client==null));
            }
        }
    }
}