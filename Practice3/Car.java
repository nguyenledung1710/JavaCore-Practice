package Practice3;

public class Car extends Vehicle {
    private final int seats;
    private final String engineType;

    public Car(String number, String manufacturer, int year, String color, Owner owner, int seats, String engineType) {
        super(number, manufacturer, year, color, owner);
        if (seats <= 0 || engineType == null || engineType.isBlank()) {
            throw new IllegalArgumentException("Seats must be positive and engine type must not be blank.");
        }
        this.seats = seats;
        this.engineType = engineType.trim();
    }

    @Override public String getType() { return "Car"; }
    @Override protected String extraInfo() { return "Seats: " + seats + ", engine: " + engineType; }
}
