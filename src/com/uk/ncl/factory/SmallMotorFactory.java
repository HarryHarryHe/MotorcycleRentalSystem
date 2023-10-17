package com.uk.ncl.factory;

import com.uk.ncl.entity.Motor;
import com.uk.ncl.entity.SmallMotorcycle;

public class SmallMotorFactory extends MotorFactory {
    @Override
    public Motor create() {
        return new SmallMotorcycle();
    }
}
