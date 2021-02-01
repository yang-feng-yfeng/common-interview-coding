package com.parking.slot;

import com.parking.FeePolicy;

import java.time.LocalDateTime;

public interface Slot {

    int getId();

    double calculateFee(FeePolicy feePolicy, LocalDateTime endOccupied);

    boolean setOccupiedStartDateTime(LocalDateTime localDateTime);

    boolean isAvailable();
}
