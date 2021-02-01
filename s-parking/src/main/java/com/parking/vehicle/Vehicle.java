package com.parking.vehicle;

import com.parking.slot.Slot;

public interface Vehicle {

    int getId();

    Slot getSlot();

    Class<? extends Slot> getSlotType();

    boolean park(Slot slot);
}
