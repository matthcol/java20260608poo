package demo.geometry.model;

import geometry.computing.GeoComputing;
import geometry.model.*;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public class DemoForms {

    @Test
    void demoFormPoint(){
//        Form form = new Form(); // 'Form' is abstract; cannot be instantiated
        Form f = new Point();
        Point pt = new Point();
        Point ptA = new Point("A", 1.0, 2.5);
        Point ptB = new Point("B");
        Point ptNoName = new Point(5.5, 7.25);
        WeightedPoint wpt = new WeightedPoint("W", 12.5, 20.75, 100.0);
        Stream.of(f, pt, ptA, ptB, ptNoName, wpt)
                .forEach(System.out::println);
        double d = ptA.distance(ptNoName);
        System.out.println("Distance: " + d);
        d = ptA.distance(wpt); // LSP
        System.out.println("Distance: " + d);
        d = wpt.distance(ptA); // inheritance
        System.out.println("Distance: " + d);

        List<Point> points = List.of(
                (Point) f, // downcasting
                pt,
                ptA,
                ptB,
                ptNoName,
                wpt
        );

        System.out.println();
        System.out.println("***** List of points *****");
        for (Point point: points){
            System.out.print(MessageFormat.format(
                    "name = {0}, x = {1}, y = {2}",
                    point.getName(),
                    point.getX(),
                    point.getY()
            ));
            if (point instanceof WeightedPoint){
                WeightedPoint pointAsWeightPoint = (WeightedPoint) point;
                System.out.print(", weight = " + pointAsWeightPoint.getWeight());
            }
            System.out.println();
        }

        // optimization Java 17
        System.out.println();
        System.out.println("***** List of points *****");
        for (Point point: points){
            System.out.print(MessageFormat.format(
                    "name = {0}, x = {1}, y = {2}",
                    point.getName(),
                    point.getX(),
                    point.getY()
            ));
            if (point instanceof WeightedPoint pointAsWeightPoint){ // pattern matching Java 17
                System.out.print(", weight = " + pointAsWeightPoint.getWeight());
            }
            System.out.println();
        }
    }

    @Test
    void demoLateBinding(){
        WeightedPoint wpt = new WeightedPoint("W", 12.5, 20.75, 100.0);
        Point pt = wpt;
        Form f = wpt;
        Object o = wpt;
        System.out.println(wpt.toString());
        System.out.println(pt.toString());
        System.out.println(f.toString());
        System.out.println(o.toString());
    }

    @Test
    void demoForms(){
        Point ptA = new Point("A", 1.0, 2.5);
        WeightedPoint wpt = new WeightedPoint("W", 12.5, 20.75, 100.0);
        Circle c = new Circle("C", ptA, 10.0);
        Circle c2 = new Circle("C", ptA, 100.0);
        Polygon triangle = new Polygon();
        List<Form> forms = List.of(ptA, wpt, c, triangle, c2);
        forms.forEach(System.out::println);

        // filter Mesurable2D => total area
        double totalArea = 0.0;
        for (Form form: forms){
            if (form instanceof Mesurable2D m){
                System.out.println("[DEBUG] mesurable2D form: " + form);
                totalArea += m.area();
            }
        }
        System.out.println("Total area: " + totalArea);

        // with GeomComputing toolbox
        totalArea = GeoComputing.totalArea(
                forms.stream()
                        .filter(form -> form instanceof Mesurable2D)
                        .map(form -> (Mesurable2D) form)
        );
        System.out.println("Total area: " + totalArea);

        List<Circle> circles = List.of(c, c2);
        totalArea = GeoComputing.totalArea(circles.stream());
        totalArea = GeoComputing.totalArea(circles); // overload Collection
        System.out.println("Total area (circles only): " + totalArea);

        totalArea = GeoComputing.totalArea(c, triangle);
        System.out.println("Total area (selection ...): " + totalArea);

        Iterable<Circle> iterableCircle = circles;
        totalArea = GeoComputing.totalArea(iterableCircle);

    }

    @Test
    void demoIterable(){
        Set<String> forms = Set.of("A1", "A2", "B1", "C0");
        for (Iterator<String> it = forms.iterator(); it.hasNext(); ){
            String form = it.next();
            System.out.println(form);
        }

    }

    @Test
    void demoPolygon(){
        Polygon polygonDefault = new Polygon();

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        var violations = validator.validate(polygonDefault);
        Assertions.assertFalse(violations.isEmpty());
        violations.forEach(System.out::println);

        // deserialization
        polygonDefault.setVertices(List.of(new Point(), new Point(), new Point()));
        violations = validator.validate(polygonDefault);
        Assertions.assertTrue(violations.isEmpty());

        //
        Point p1 = new Point("A", 1, 1);
        Point p2 = new Point("B", 4, 1);
        Point p3 = new Point("C", 4, 5);
        Point p4 = new Point("D", 1, 4);
        Polygon triangle = new Polygon("ABC", List.of(p1, p2, p3));
        Polygon rectangle = Polygon.of("ABCD", p1, p2, p3, p4);
        System.out.println(triangle);
        System.out.println(rectangle);

        System.out.println("Rectangle vertices");
        for (Point vertex: rectangle){
            System.out.println(" - " + vertex);
        }

        System.out.println();
        rectangle.forEachVertex(System.out::println);

        double minX = rectangle.streamVertex()
                .mapToDouble(Point::getX)
                .min()
                .orElseThrow(); // should not happen
        System.out.println();
        System.out.println("Min X: " + minX);
    }

    @Test
    void demoPatternMatchingSwitch(){
        Point ptA = new Point("A", 1.0, 2.5);
        WeightedPoint wpt = new WeightedPoint("W", 12.5, 20.75, 100.0);
        Circle c = new Circle("C", ptA, 10.0);
        Circle c2 = new Circle("C", ptA, 100.0);
        Polygon triangle = new Polygon();
        List<Form> forms = List.of(ptA, wpt, c, triangle, c2);
        for (var form: forms){
            System.out.println(form);
            // Pattern matching Java 17-21
            // https://docs.oracle.com/en/java/javase/21/language/sealed-classes-and-interfaces.html
            // https://docs.oracle.com/en/java/javase/21/language/pattern-matching.html
            switch (form) {
                case WeightedPoint wp -> System.out.println("Weighted point with weight: " + wp.getWeight());
                case Point point -> System.out.println("Point with x: " + point.getX());
                case Mesurable2D m -> System.out.println("Mesurable 2D with area: " + m.area());
                default -> System.out.println("Other form (future): " + form); // or close model (sealed)
            }
            System.out.println();
        }
    }

}
