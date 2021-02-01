package com.parking.vehicle;

import com.parking.slot.Slot;
import com.parking.slot.Slot30Kw;

import java.time.LocalDateTime;

public class Vehicle30Kw implements Vehicle{

    private final int id;
    private Slot slot = null;

    public Vehicle30Kw(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public Slot getSlot() {
        return slot;
    }

    @Override
    public Class<? extends Slot> getSlotType() {
        return Slot30Kw.class;
    }

    @Override
    public boolean park(Slot slot) {
        slot.setOccupiedStartDateTime(LocalDateTime.now());
        this.slot = slot;
        return true;
    }
}
