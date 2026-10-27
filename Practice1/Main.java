package Practice1;
public class Main {

    public static void main(String[] args) {

        Shape shape = new Shape(5, 10);

        Rectangle rectangle = new Rectangle(4, 6);

        Circle circle = new Circle(3);

        System.out.println("=== SHAPE ===");
        shape.displayInfo();

        System.out.println();

        System.out.println("=== RECTANGLE ===");
        rectangle.displayInfo();

        System.out.println();

        System.out.println("=== CIRCLE ===");
        circle.displayInfo();
    }
}