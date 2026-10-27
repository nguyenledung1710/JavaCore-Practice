package Practice3;

public class Truck extends Vehicle {
    private final double tonnage;

    public Truck(String number, String manufacturer, int year, String color, Owner owner, double tonnage) {
        super(number, manufacturer, year, color, owner);
        if (!Double.isFinite(tonnage) || tonnage <= 0) {
            throw new IllegalArgumentException("Tonnage must be positive.");
        }
        this.tonnage = tonnage;
    }

    @Override public String getType() { return "Truck"; }
    @Override protected String extraInfo() { return "Tonnage: " + tonnage + " tons"; }
}
