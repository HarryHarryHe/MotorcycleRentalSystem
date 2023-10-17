package com.uk.ncl.factory;

import com.uk.ncl.po.Motor;
import com.uk.ncl.po.SmallMotorcycle;

public class SmallMotorFactory extends MotorFactory {
    @Override
    public Motor create() {
        return new SmallMotorcycle();
    }
}
