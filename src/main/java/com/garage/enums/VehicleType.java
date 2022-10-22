package com.garage.enums;

import java.util.HashMap;
import java.util.Map;

public enum VehicleType {
    CAR(1),
    JEEP(2),
    TRUCK(4);

    private Integer size;
    private static final Map<Integer, VehicleType> lookup = new HashMap<Integer, VehicleType>();

    static {
        for (VehicleType d : VehicleType.values()) {
            lookup.put(d.getSize(), d);
        }
    }
    public static VehicleType get(int size) {
        return lookup.get(size);
    }

    public Integer getSize() {
        return size;
    }

    VehicleType(Integer size) {
        this.size = size;
    }
}
