package com.parking;

import com.parking.vehicle.Vehicle;

public interface Parking {

    /**
     *
     * @return -1 for no place, otherwise the number of the parking lot
     */
    int vehicleIn(Vehicle vehicle);

    double vehicleOut(Vehicle vehicle);

    double vehicleOut(int vehicleId);
}
