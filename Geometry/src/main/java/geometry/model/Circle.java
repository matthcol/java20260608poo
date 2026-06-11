package geometry.model;

import java.text.MessageFormat;

public class Circle extends Form implements Mesurable2D{
    private Point center;
    private double radius;

    public Circle() {
    }

    public Circle(String name, Point center, double radius) {
        super(name);
        this.center = center;
        this.radius = radius;
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        center.translate(deltaX, deltaY);
    }

    @Override
    public double perimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "{0}<center: {1} ; radius: {2}>",
                getName(),
                center,
                radius
        );
    }
}
