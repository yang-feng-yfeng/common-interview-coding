package com.parking;

import com.parking.vehicle.Vehicle30Kw;
import org.junit.Assert;
import org.junit.Test;

public class ParkingImplTest {


    @Test
    public void simpleTest() throws Exception{
        DefaultFeePolicy defaultFeePolicy = new DefaultFeePolicy();
        ParkingImpl parking = new ParkingImpl(defaultFeePolicy, 0, 2, 0);
        Vehicle30Kw vehicle30Kw = new Vehicle30Kw(0);
        Assert.assertEquals(0, parking.vehicleIn(vehicle30Kw));

        Thread.sleep(1000);
        Vehicle30Kw vehicle30Kw1 = new Vehicle30Kw(1);
        Vehicle30Kw vehicle30Kw2 = new Vehicle30Kw(2);

        Assert.assertEquals(1, parking.vehicleIn(vehicle30Kw1));
        // Out of parking lot
        Assert.assertEquals(-1, parking.vehicleIn(vehicle30Kw2));

        parking.vehicleOut(vehicle30Kw1);
        double price = parking.vehicleOut(0);
        Assert.assertTrue(price >= 500);
    }

    @Test
    public void vehicleIn() {
    }

    @Test
    public void vehicleOut() {
    }
}