package com.parking.slot;

import com.parking.FeePolicy;

import java.time.Duration;
import java.time.LocalDateTime;

public abstract class SlotDefaultImpl implements Slot{

    private final int id;
    protected LocalDateTime occupiedStartTime = null;

    public SlotDefaultImpl(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public double calculateFee(FeePolicy feePolicy, LocalDateTime endOccupied) {
        if (occupiedStartTime == null) {
            // Should never happened.
            throw new UnsupportedOperationException("This slot is available so no fee to calculate.");
        }
        return feePolicy.fee(this, Duration.between(occupiedStartTime, endOccupied));
    }

    @Override
    public boolean isAvailable() {
        return occupiedStartTime == null;
    }

    /**
     * If occupied, return false, otherwise return true;
     */
    @Override
    public boolean setOccupiedStartDateTime(LocalDateTime localDateTime) {
        if (this.occupiedStartTime != null) {
            return false;
        }
        else {
            this.occupiedStartTime = localDateTime;
            return true;
        }
    }
}
