package com.uk.ncl.factory;

import com.uk.ncl.entity.Client;
import com.uk.ncl.entity.LargeMotorcycle;
import com.uk.ncl.entity.Motor;
import com.uk.ncl.entity.RentalCompany;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class LargeMotorFactoryTest {
    RentalCompany rentalCompany = RentalCompany.getRentalCompany();

    @Test
    public void testCreate() {
        LargeMotorFactory motorFactory = new LargeMotorFactory();
        boolean isOk = motorFactory.create();
        List<Motor> motorcycles = motorFactory.getMotorcycles();
        for (Motor motorcycle : motorcycles) {
            System.out.println(motorcycle);
        }
        List<HashMap<Motor, Client>> largeMotorWithClientList = rentalCompany.getLargeMotorWithClientList();
        for (Iterator<HashMap<Motor, Client>> iterator = largeMotorWithClientList.iterator();
             iterator.hasNext(); ) {
            HashMap<Motor, Client> next = iterator.next();
            for (Map.Entry<Motor, Client> entry : next.entrySet()) {
                Client client = entry.getValue();
                System.out.println(entry.getKey() + ": " + (client==null));
            }
        }
    }

}