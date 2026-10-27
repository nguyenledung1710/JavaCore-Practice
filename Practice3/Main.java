package Practice3;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SC = new Scanner(System.in);

    public static void main(String[] args) {
        VehicleManager manager = new VehicleManager();
        while (true) {
            System.out.println("\n===== VEHICLE MANAGEMENT =====");
            System.out.println("1. Add a vehicle");
            System.out.println("2. Search by vehicle number");
            System.out.println("3. Find vehicles by owner ID");
            System.out.println("4. Delete vehicles by manufacturer");
            System.out.println("5. Manufacturer with most vehicles");
            System.out.println("6. Manufacturers sorted by vehicle count (descending)");
            System.out.println("7. Statistics by vehicle type");
            System.out.println("8. List all vehicles");
            System.out.println("0. Exit");
            int choice = readInt("Choose: ");
            try {
                switch (choice) {
                    case 1: addVehicle(manager); break;
                    case 2:
                        Vehicle found = manager.searchByNumber(readText("Vehicle number: "));
                        System.out.println(found == null ? "Not found." : found);
                        break;
                    case 3:
                        List<Vehicle> matches = manager.searchByOwnerId(readText("Owner ID (12 digits): "));
                        if (matches.isEmpty()) System.out.println("No vehicles found.");
                        else matches.forEach(System.out::println);
                        break;
                    case 4:
                        System.out.println("Deleted: " + manager.deleteByManufacturer(readText("Manufacturer: ")) + " vehicle(s).");
                        break;
                    case 5: manager.showMostPopularManufacturer(); break;
                    case 6: manager.showManufacturersByCountDescending(); break;
                    case 7: manager.showTypeStatistics(); break;
                    case 8: manager.showAll(); break;
                    case 0:
                        System.out.println("Goodbye!");
                        return;
                    default: System.out.println("Invalid choice.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid data: " + e.getMessage());
            }
        }
    }

    private static void addVehicle(VehicleManager manager) {
        System.out.println("Vehicle type: 1. Car  2. Motorbike  3. Truck");
        int type = readInt("Type: ");
        if (type < 1 || type > 3) { System.out.println("Invalid vehicle type."); return; }
        String number = readText("Vehicle number (5 characters): ");
        if (manager.searchByNumber(number) != null) { System.out.println("Duplicate vehicle number."); return; }
        String maker = readText("Manufacturer (Honda/Yamaha/Toyota/Suzuki): ");
        int year = readInt("Manufacture year: ");
        String color = readText("Color: ");
        String id = readText("Owner ID (12 digits): ");
        Owner existing = manager.findOwner(id);
        Owner owner;
        if (existing != null) {
            System.out.println("Existing owner: " + existing);
            owner = existing;
        } else {
            String name = readText("Owner full name: ");
            String email = readText("Owner email: ");
            owner = new Owner(id, name, email);
        }
        Vehicle vehicle;
        if (type == 1) {
            vehicle = new Car(number, maker, year, color, owner,
                    readInt("Number of seats: "), readText("Engine type: "));
        } else if (type == 2) {
            vehicle = new Motorbike(number, maker, year, color, owner, readDouble("Capacity (cc): "));
        } else {
            vehicle = new Truck(number, maker, year, color, owner, readDouble("Tonnage (tons): "));
        }
        System.out.println(manager.add(vehicle) ? "Added successfully!" : "Duplicate vehicle number.");
    }

    private static String readText(String prompt) {
        System.out.print(prompt);
        return SC.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            try { return Integer.parseInt(readText(prompt)); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid integer."); }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            try { return Double.parseDouble(readText(prompt)); }
            catch (NumberFormatException e) { System.out.println("Please enter a valid number."); }
        }
    }
}
