package com.uk.ncl.factory;

import com.uk.ncl.constant.MyConstants;
import com.uk.ncl.entity.Motor;
import com.uk.ncl.entity.SmallMotorcycle;

import java.util.List;

public class SmallMotorFactory extends MotorFactory {
    private int rentedMaxNum = MyConstants.RENTED_SMALL_MAX_NUM;
    @Override
    public List<Motor> create() {
        return null;
    }
}
