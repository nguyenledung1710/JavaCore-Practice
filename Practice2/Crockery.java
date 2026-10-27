package Practice2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Crockery extends Goods {
    private final String manufacturer;
    private final LocalDate arrivalDate;

    public Crockery(String code, String name, int quantity, double unitPrice,
                    String manufacturer, LocalDate arrivalDate) {
        super(code, name, quantity, unitPrice);
        if (manufacturer == null || manufacturer.isBlank()) {
            throw new IllegalArgumentException("Manufacturer cannot be empty");
        }
        if (arrivalDate == null || arrivalDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid arrival date");
        }
        this.manufacturer = manufacturer;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public double calculateVAT() { return getTotalPrice() * 0.10; }

    @Override
    public String evaluateConsumption() {
        long days = ChronoUnit.DAYS.between(arrivalDate, LocalDate.now());
        return getQuantity() > 50 && days > 10 ? "Slow sale" : "Not evaluated";
    }
}
