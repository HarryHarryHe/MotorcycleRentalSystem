package com.uk.ncl.factory;

import com.uk.ncl.entity.LargeMotorcycle;
import com.uk.ncl.entity.Motor;

public class LargeMotorFactory extends MotorFactory {
    @Override
    public Motor create() {
        return new LargeMotorcycle();
    }
}
