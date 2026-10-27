package Practice2;

import java.time.LocalDate;

public class Food extends Goods {
    private final LocalDate manufactureDate;
    private final LocalDate expirationDate;
    private final String supplier;

    public Food(String code, String name, int quantity, double unitPrice,
                LocalDate manufactureDate, LocalDate expirationDate, String supplier) {
        super(code, name, quantity, unitPrice);
        if (manufactureDate == null || expirationDate == null || expirationDate.isBefore(manufactureDate)) {
            throw new IllegalArgumentException("Invalid manufacture/expiration date");
        }
        if (supplier == null || supplier.isBlank()) {
            throw new IllegalArgumentException("Supplier cannot be empty");
        }
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
    }

    @Override
    public double calculateVAT() { return getTotalPrice() * 0.05; }

    @Override
    public String evaluateConsumption() {
        return getQuantity() > 0 && expirationDate.isBefore(LocalDate.now()) ? "Hard to sell" : "Not evaluated";
    }
}
