package com.uk.ncl.factory;

import com.uk.ncl.po.RentalContract;
import com.uk.ncl.po.RentalManager;

public abstract class RentalCompany {
    private String regNum;
    private int battery;
    private int kilometers;

    private int motorNum;

    private RentalManager rentalManager;
    private RentalContract rentalContract;
}
