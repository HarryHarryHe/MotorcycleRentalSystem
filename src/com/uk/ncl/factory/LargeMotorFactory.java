package com.uk.ncl.factory;

import com.uk.ncl.po.LargeMotorcycle;
import com.uk.ncl.po.Motor;

public class LargeMotorFactory extends MotorFactory {
    @Override
    public Motor create() {
        return new LargeMotorcycle();
    }
}
