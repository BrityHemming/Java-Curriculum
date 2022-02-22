import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise06 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Loop over each vehicle in `vehicleMap` and print vehicles with a Dodge make.
        // 2. Loop three times with three different techniques: .values(), .entrySet(), and .keySet().
    }
}
