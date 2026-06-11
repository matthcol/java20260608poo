package geometry.model;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;

@Getter
@Setter
public class Polygon extends Form implements Mesurable2D, Iterable<Point>{

    @Size(min=3)
    private List<Point> vertices;

    public Polygon() {
        vertices = new ArrayList<>();
    }

    public Polygon(String name, Collection<? extends Point> vertices) {
        super(name);
        this.vertices = new ArrayList<>(vertices);
    }

    public static Polygon of(String name, Point p1, Point p2, Point p3, Point... others){
        List<Point> vertices = new ArrayList<>(3 + others.length);
        Collections.addAll(vertices, p1, p2, p3);
        Collections.addAll(vertices, others);
        Polygon polygon = new Polygon();
        polygon.setName(name);
        polygon.setVertices(vertices);
        return polygon;
    }

    @Override
    public void translate(double deltaX, double deltaY) {
        // TODO
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

    // TODO: nbVertex, vertexAt, ...

    @Override
    public Iterator<Point> iterator() {
        return vertices.iterator();
    }

    public void forEachVertex(Consumer<? super Point> consumer){
        vertices.forEach(consumer);
    }

    public Stream<Point> streamVertex(){
        return vertices.stream();
    }
}
