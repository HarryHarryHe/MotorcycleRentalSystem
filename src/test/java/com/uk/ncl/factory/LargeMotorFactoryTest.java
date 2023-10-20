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
    //singleton model
//    MotorFactory motorFactory = new LargeMotorFactory();
//    boolean isOk = motorFactory.create();
    @Test
    public void testCreate() {
        MotorFactory largeMotorFactory = new LargeMotorFactory();
        //create largeMotorCycles through largeMotorFactory
        assertTrue(largeMotorFactory.create());
        //total amount: 10
        List<Motor> motorcycles = largeMotorFactory.getMotorcycles();
        for (Motor motorcycle : motorcycles) {
            System.out.println(motorcycle);
        }
    }

}