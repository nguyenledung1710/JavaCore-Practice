package Practice2;

public class Electronics extends Goods {
    private final int warrantyMonths;
    private final double powerKW;

    public Electronics(String code, String name, int quantity, double unitPrice,
                       int warrantyMonths, double powerKW) {
        super(code, name, quantity, unitPrice);
        if (warrantyMonths < 0 || !Double.isFinite(powerKW) || powerKW < 0) {
            throw new IllegalArgumentException("Invalid warranty or power");
        }
        this.warrantyMonths = warrantyMonths;
        this.powerKW = powerKW;
    }

    @Override
    public double calculateVAT() { return getTotalPrice() * 0.10; }

    @Override
    public String evaluateConsumption() {
        return getQuantity() < 3 ? "Sold" : "Not evaluated";
    }
}
