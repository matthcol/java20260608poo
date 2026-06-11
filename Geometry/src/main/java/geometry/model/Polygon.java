package geometry.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class Polygon extends Form implements Mesurable2D{

    @Size(min=3)
    private List<Point> vertices;

    public Polygon() {
        vertices = new ArrayList<>();
    }

    public Polygon(String name, Collection<? extends Point> vertices) {
        super(name);
        this.vertices = new ArrayList<>(vertices);
    }

    @Override
    public void translate(double deltaX, double deltaY) {

    }

    @Override
    public double perimeter() {
        // TODO
        return 2.0;
    }

    @Override
    public double area() {
        // TODO
        return 1.0;
    }
}
