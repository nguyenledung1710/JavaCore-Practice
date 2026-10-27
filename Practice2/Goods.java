package Practice2;

public abstract class Goods {
    private final String code;
    private final String name;
    private final int quantity;
    private final double unitPrice;

    public Goods(String code, String name, int quantity, double unitPrice) {
        if (code == null || code.isBlank() || name == null || name.isBlank()) {
            throw new IllegalArgumentException("Code and name cannot be empty");
        }
        if (quantity < 0 || !Double.isFinite(unitPrice) || unitPrice < 0) {
            throw new IllegalArgumentException("Quantity and price must be non-negative");
        }
        this.code = code.trim();
        this.name = name.trim();
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getCode() { return code; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotalPrice() { return quantity * unitPrice; }

    public abstract double calculateVAT();
    public abstract String evaluateConsumption();

    public void displayInfo() {
        System.out.printf("Code: %s | Name: %s | Quantity: %d | Price: %.2f | VAT: %.2f | Evaluation: %s%n",
                code, name, quantity, unitPrice, calculateVAT(), evaluateConsumption());
    }
}
