package Practice2;

public class DSHH {
    private final Goods[] items;
    private int count;

    public DSHH(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        items = new Goods[capacity];
    }

    public boolean addGoods(Goods goods) {
        if (goods == null || count >= items.length) return false;
        for (int i = 0; i < count; i++) {
            if (items[i].getCode().equalsIgnoreCase(goods.getCode())) return false;
        }
        items[count++] = goods;
        return true;
    }

    public void displayAll() {
        if (count == 0) {
            System.out.println("Inventory is empty.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + ". ");
            items[i].displayInfo();
        }
    }

    public void displaySummary() {
        int totalQuantity = 0;
        double totalVAT = 0;
        for (int i = 0; i < count; i++) {
            totalQuantity += items[i].getQuantity();
            totalVAT += items[i].calculateVAT();
        }
        System.out.println("Number of products: " + count);
        System.out.println("Total inventory quantity: " + totalQuantity);
        System.out.printf("Total VAT: %.2f%n", totalVAT);
    }
}
