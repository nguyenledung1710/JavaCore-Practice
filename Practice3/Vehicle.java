package Practice3;

import java.time.Year;
import java.util.Set;

public abstract class Vehicle {
    private static final Set<String> MANUFACTURERS = Set.of("Honda", "Yamaha", "Toyota", "Suzuki");
    private final String vehicleNumber;
    private final String manufacturer;
    private final int manufactureYear;
    private final String color;
    private final Owner owner;

    protected Vehicle(String vehicleNumber, String manufacturer, int manufactureYear, String color, Owner owner) {
        if (vehicleNumber == null || vehicleNumber.length() != 5 || vehicleNumber.isBlank()) {
            throw new IllegalArgumentException("Vehicle number must contain exactly 5 characters.");
        }
        if (manufacturer == null || MANUFACTURERS.stream().noneMatch(m -> m.equalsIgnoreCase(manufacturer.trim()))) {
            throw new IllegalArgumentException("Manufacturer must be Honda, Yamaha, Toyota or Suzuki.");
        }
        if (manufactureYear <= 2000 || manufactureYear > Year.now().getValue()) {
            throw new IllegalArgumentException("Manufacture year must be greater than 2000 and not in the future.");
        }
        if (color == null || color.isBlank()) {
            throw new IllegalArgumentException("Color cannot be blank.");
        }
        if (owner == null) {
            throw new IllegalArgumentException("Owner is required.");
        }
        this.vehicleNumber = vehicleNumber.trim();
        this.manufacturer = MANUFACTURERS.stream().filter(m -> m.equalsIgnoreCase(manufacturer.trim())).findFirst().orElseThrow();
        this.manufactureYear = manufactureYear;
        this.color = color.trim();
        this.owner = owner;
    }

    public String getVehicleNumber() { return vehicleNumber; }
    public String getManufacturer() { return manufacturer; }
    public Owner getOwner() { return owner; }
    public abstract String getType();
    protected abstract String extraInfo();

    @Override
    public String toString() {
        return getType() + " | Number: " + vehicleNumber + " | Manufacturer: " + manufacturer
                + " | Year: " + manufactureYear + " | Color: " + color + " | Owner: " + owner
                + " | " + extraInfo();
    }
}
