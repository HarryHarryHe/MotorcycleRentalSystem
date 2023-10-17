package com.uk.ncl.factory;

import com.uk.ncl.po.Motor;

import java.util.List;

public abstract class MotorFactory {
    public abstract List<Motor> create();
}
