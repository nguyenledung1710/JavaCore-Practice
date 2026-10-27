package Practice3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VehicleManager {
    private final List<Vehicle> vehicles = new ArrayList<>();
    private final Map<String, Owner> owners = new LinkedHashMap<>();
    private static final String[] MAKERS = {"Honda", "Yamaha", "Toyota", "Suzuki"};

    public Owner findOwner(String id) { return owners.get(id); }

    public Vehicle searchByNumber(String number) {
        for (Vehicle v : vehicles) {
            if (v.getVehicleNumber().equalsIgnoreCase(number)) return v;
        }
        return null;
    }

    public boolean add(Vehicle vehicle) {
        if (searchByNumber(vehicle.getVehicleNumber()) != null) return false;
        Owner existing = owners.get(vehicle.getOwner().getIdNumber());
        if (existing != null && (!existing.getFullName().equals(vehicle.getOwner().getFullName())
                || !existing.getEmail().equalsIgnoreCase(vehicle.getOwner().getEmail()))) {
            throw new IllegalArgumentException("This owner ID already belongs to another owner record.");
        }
        owners.putIfAbsent(vehicle.getOwner().getIdNumber(), vehicle.getOwner());
        vehicles.add(vehicle);
        return true;
    }

    public List<Vehicle> searchByOwnerId(String id) {
        List<Vehicle> matches = new ArrayList<>();
        for (Vehicle v : vehicles) {
            if (v.getOwner().getIdNumber().equals(id)) matches.add(v);
        }
        return matches;
    }

    public int deleteByManufacturer(String manufacturer) {
        int before = vehicles.size();
        vehicles.removeIf(v -> v.getManufacturer().equalsIgnoreCase(manufacturer));
        // Remove owner records no longer referenced by a vehicle.
        owners.entrySet().removeIf(e -> searchByOwnerId(e.getKey()).isEmpty());
        return before - vehicles.size();
    }

    public Map<String, Integer> manufacturerCounts() {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (String maker : MAKERS) counts.put(maker, 0);
        for (Vehicle v : vehicles) counts.put(v.getManufacturer(), counts.get(v.getManufacturer()) + 1);
        return counts;
    }

    public void showMostPopularManufacturer() {
        Map<String, Integer> counts = manufacturerCounts();
        int max = counts.values().stream().mapToInt(Integer::intValue).max().orElse(0);
        if (max == 0) {
            System.out.println("No vehicles in the system.");
            return;
        }
        System.out.println("Manufacturer(s) with most vehicles (" + max + "):");
        counts.forEach((maker, count) -> { if (count == max) System.out.println("- " + maker); });
    }

    public void showManufacturersByCountDescending() {
        Map<String, Integer> counts = manufacturerCounts();
        counts.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, Integer>>comparingInt(Map.Entry::getValue)
                        .reversed().thenComparing(Map.Entry::getKey))
                .forEach(e -> System.out.println(e.getKey() + ": " + e.getValue()));
    }

    public void showTypeStatistics() {
        int cars = 0, motorbikes = 0, trucks = 0;
        for (Vehicle v : vehicles) {
            if (v instanceof Car) cars++;
            else if (v instanceof Motorbike) motorbikes++;
            else if (v instanceof Truck) trucks++;
        }
        System.out.println("Cars: " + cars + " | Motorbikes: " + motorbikes + " | Trucks: " + trucks);
        System.out.println("Total: " + vehicles.size());
    }

    public void showAll() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the system.");
            return;
        }
        for (Vehicle v : vehicles) System.out.println(v);
    }
}
