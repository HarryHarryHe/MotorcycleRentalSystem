package com.uk.ncl.po;

import com.uk.ncl.po.RentalContract;
import com.uk.ncl.po.RentalManager;

import java.util.HashMap;
import java.util.List;

public class RentalCompany {
    private int availableLargeNum;
    private int availableSmallNum;
    private int largeRentedNum = 0;
    private int SmallRentedNum = 0;
    private List<HashMap<Motor, Client>> motorWithClientList;

}
