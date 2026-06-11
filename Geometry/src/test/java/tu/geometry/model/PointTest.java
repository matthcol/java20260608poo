package tu.geometry.model;

import geometry.model.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {

    @Test
    void testDistance_withMargin(){
        // given
        Point A = new Point("A",-2,-2);
        Point B = new Point("B",2,2);
        // when
        double d = A.distance(B);
        // then
        assertEquals(5.65685424949238, d, 1E-10);
    }

    @Test
    void testDistance_triangle345(){
        // given
        Point A = new Point("A",-2.0,-2.25);
        Point B = new Point("B",1.0,-6.25);
        // when
        double d = A.distance(B);
        // then
        assertEquals(5.0, d);
    }

    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "same point, 1.5, 2.25, 1.5, 2.25, 0.0, 0.0",
            "triangle 345, 1.5, 2.25, 5.5, 5.25, 5.0, 0.0",
            "triangle 345 E307, 1.5E307, 2.25E307, 5.5E307, 5.25E307, 5.0E307, 0.0",
            "triangle 345 E-307, 1.5E-307, 2.25E-307, 5.5E-307, 5.25E-307, 5.0E-307, 1E-320"
    })
    void testDistance(String label, double xA, double yA, double xB, double yB, double expectedDistance, double margin){
        // given
        Point ptA = new Point("A", xA, yA);
        Point ptB = new Point("B", xB, yB);
        // when
        double actualDistance = ptA.distance(ptB);
        // then
        assertEquals(expectedDistance, actualDistance, margin);
    }

}