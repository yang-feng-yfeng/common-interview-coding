package com.parking;

import com.parking.slot.Slot;

import java.time.Duration;

public interface FeePolicy {

    double fee(Slot slot, Duration duration);
}
