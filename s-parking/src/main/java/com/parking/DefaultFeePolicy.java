package com.parking;

import com.parking.slot.Slot;

import java.time.Duration;

public class DefaultFeePolicy implements FeePolicy {
    @Override
    public double fee(Slot slot, Duration duration) {
        // Very easy impl
        return duration.toMillis() * 0.5;
    }
}
