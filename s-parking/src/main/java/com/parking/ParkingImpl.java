package com.parking;

import com.parking.slot.Slot;
import com.parking.slot.Slot30Kw;
import com.parking.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.util.*;

public class ParkingImpl implements Parking {

    private final FeePolicy feePolicy;
    private final Map<Class<? extends Slot>, List<Slot>> slotsMap = new HashMap<>();
    private final Map<Integer, Vehicle> parked = new HashMap<>();

    public ParkingImpl(FeePolicy feePolicy, int nbStandardSlot, int nbSlot30Kw, int nbSlot50Kw) {
        this.feePolicy = feePolicy;
        initSlot(nbStandardSlot, nbSlot30Kw, nbSlot50Kw);
    }

    private void initSlot(int nbStandardSlot, int nbSlot30Kw, int nbSlot50Kw) {
        int i = 0;
//        for (; i < nbStandardSlot; i++) {
//            slotsStandards.add(new SlotStandard(i));
//        }
        List<Slot> slots = this.slotsMap.getOrDefault(Slot30Kw.class, new ArrayList<>());
        for (; i < nbSlot30Kw; i++) {
            slots.add(new Slot30Kw(i));
        }
        this.slotsMap.put(Slot30Kw.class, slots);
//        for (; i < nbSlot50Kw; i++) {
//            slots50Kw.add(new Slot50Kw(i));
//        }
    }

    @Override
    public int vehicleIn(Vehicle vehicle) {
        if (vehicle.getSlot() != null) {
            // Should never happened.
            throw new UnsupportedOperationException("This vehicle is already parked.");
        }
        Class<? extends Slot> slotType = vehicle.getSlotType();
        List<Slot> slots = this.slotsMap.getOrDefault(slotType, Collections.emptyList());
        // Find availability
        for (Slot slot : slots) {
            if (slot.isAvailable()) {
                vehicle.park(slot);
                parked.put(vehicle.getId(), vehicle);
                return slot.getId();
            }
        }
        return -1;
    }

    @Override
    public double vehicleOut(Vehicle vehicle) {
        return vehicleOut(vehicle.getId());
    }


    @Override
    public double vehicleOut(int vehicleId) {
        Vehicle vehicle = parked.get(vehicleId);
        if (vehicle == null || vehicle.getSlot() == null) {
            // Should never happened.
            throw new UnsupportedOperationException("This vehicle is not yet parked.");
        }
        parked.remove(vehicle.getId());
        return vehicle.getSlot().calculateFee(feePolicy, LocalDateTime.now());
    }
}
