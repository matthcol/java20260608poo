package geometry.model;

import lombok.Getter;
import lombok.Setter;

import java.text.MessageFormat;
import java.util.Objects;

@Getter
@Setter
public class Point extends Form {
    private double x;
    private double y;

    public Point() {
        // implicit call:  super()
    }

    public Point(String name) {
        super(name);
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(String name, double x, double y) {
        super(name); // calls constructor from parent class: Form
        this.x = x;
        this.y = y;
    }

    public double distance(Point other){
        double deltaX = other.getX() - this.x;
        double deltaY = other.getY() - this.y;
        return Math.hypot(deltaX,  deltaY);
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        // this.x += deltaX;
        x += deltaX;
        y += deltaY;
    }

    @Override
    public String toString() {
        return MessageFormat.format(
                "{0}({1}, {2})",
//                getName() == null ? "?": getName(),
                Objects.toString(getName(), "?"),
                x,
                y
        );
    }
}
