package Practice2;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        DSHH manager = new DSHH(100);
        int choice;
        do {
            System.out.println("\n===== SUPERMARKET INVENTORY =====");
            System.out.println("1. Add Food");
            System.out.println("2. Add Electronics");
            System.out.println("3. Add Crockery");
            System.out.println("4. Display all goods");
            System.out.println("5. Inventory summary");
            System.out.println("0. Exit");
            choice = readInt("Choose: ");
            try {
                Goods goods = null;
                switch (choice) {
                    case 1: goods = inputFood(); break;
                    case 2: goods = inputElectronics(); break;
                    case 3: goods = inputCrockery(); break;
                    case 4: manager.displayAll(); break;
                    case 5: manager.displaySummary(); break;
                    case 0: System.out.println("Goodbye!"); break;
                    default: System.out.println("Invalid choice!");
                }
                if (goods != null) {
                    System.out.println(manager.addGoods(goods)
                            ? "Added successfully!" : "Duplicate code or inventory full!");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 0);
    }

    private static String readString(String message) {
        System.out.print(message);
        return sc.nextLine().trim();
    }

    private static int readInt(String message) {
        while (true) {
            try { return Integer.parseInt(readString(message)); }
            catch (NumberFormatException e) { System.out.println("Please enter an integer!"); }
        }
    }

    private static double readDouble(String message) {
        while (true) {
            try {
                double n = Double.parseDouble(readString(message));
                if (!Double.isFinite(n)) throw new NumberFormatException();
                return n;
            } catch (NumberFormatException e) { System.out.println("Please enter a valid number!"); }
        }
    }

    private static LocalDate readDate(String message) {
        while (true) {
            try { return LocalDate.parse(readString(message)); }
            catch (java.time.format.DateTimeParseException e) {
                System.out.println("Invalid date! Use yyyy-MM-dd.");
            }
        }
    }

    private static Food inputFood() {
        String code = readString("Code: ");
        String name = readString("Name: ");
        int quantity = readInt("Quantity: ");
        double price = readDouble("Unit price: ");
        LocalDate manufactured = readDate("Manufacture date (yyyy-MM-dd): ");
        LocalDate expired = readDate("Expiration date (yyyy-MM-dd): ");
        String supplier = readString("Supplier: ");
        return new Food(code, name, quantity, price, manufactured, expired, supplier);
    }

    private static Electronics inputElectronics() {
        String code = readString("Code: ");
        String name = readString("Name: ");
        int quantity = readInt("Quantity: ");
        double price = readDouble("Unit price: ");
        int warranty = readInt("Warranty (months): ");
        double power = readDouble("Power (kW): ");
        return new Electronics(code, name, quantity, price, warranty, power);
    }

    private static Crockery inputCrockery() {
        String code = readString("Code: ");
        String name = readString("Name: ");
        int quantity = readInt("Quantity: ");
        double price = readDouble("Unit price: ");
        String manufacturer = readString("Manufacturer: ");
        LocalDate arrival = readDate("Arrival date (yyyy-MM-dd): ");
        return new Crockery(code, name, quantity, price, manufacturer, arrival);
    }
}
