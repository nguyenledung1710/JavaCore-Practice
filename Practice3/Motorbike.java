package Practice3;

public class Motorbike extends Vehicle {
    private final double capacityCC;

    public Motorbike(String number, String manufacturer, int year, String color, Owner owner, double capacityCC) {
        super(number, manufacturer, year, color, owner);
        if (!Double.isFinite(capacityCC) || capacityCC <= 0) {
            throw new IllegalArgumentException("Engine capacity must be positive.");
        }
        this.capacityCC = capacityCC;
    }

    @Override public String getType() { return "Motorbike"; }
    @Override protected String extraInfo() { return "Capacity: " + capacityCC + " cc"; }
}
