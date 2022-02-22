import learn.Vehicle;
import learn.VehicleRepository;

import java.util.HashMap;

public class Exercise04 {

    public static void main(String[] args) {
        HashMap<String, Vehicle> vehicleMap = VehicleRepository.getMap();

        // 1. Create a new Vehicle. Use a VIN that's easy to remember.
        // 2. Add the Vehicle to `vehicleMap` with the `put` method.
        // 3. Confirm the Vehicle was added by retrieving it with `get` and printing it to the console.
    }
}
