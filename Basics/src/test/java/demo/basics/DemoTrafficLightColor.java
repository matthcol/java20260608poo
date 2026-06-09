package demo.basics;

import enums.TrafficLightColor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class DemoTrafficLightColor {

    @Test
    void demo1(){
        TrafficLightColor trafficLightColor = TrafficLightColor.GREEN;

        System.out.println(trafficLightColor); // call .toString()
        System.out.println(trafficLightColor.toString());

        System.out.println("Traffic light: " + trafficLightColor); // call .toString()
        System.out.println("Traffic light: " + trafficLightColor.toString());

        String trafficLightColorStr = "RED";
        trafficLightColor = TrafficLightColor.valueOf(trafficLightColorStr);
        System.out.println(trafficLightColor);

        System.out.println("Ordinal: " + trafficLightColor.ordinal());
        int ordinal = 1;
        trafficLightColor = TrafficLightColor.values()[ordinal];
        System.out.println(trafficLightColor);
    }

    // test exception valueOf
    @Test
    void valueOf_shouldThrow_whenValueNotExist() {
        assertThrows(IllegalArgumentException.class, () -> TrafficLightColor.valueOf("BLUE"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"BLUE", "red", "ReD", "Red"})
    void valueOf_shouldThrow_whenValuesNotExist(String valueStr) {
        assertThrows(IllegalArgumentException.class, () -> TrafficLightColor.valueOf(valueStr));
    }

    // test exception values()[]

    @ParameterizedTest
    @ValueSource(ints = {-1, 3, 12, 13, 14, 10})
    void values_shouldThrowArray_whenOut(int index) {
        assertThrows(
                ArrayIndexOutOfBoundsException.class,
                () -> {
                    TrafficLightColor _ = TrafficLightColor.values()[index];
                }
        );
    }

    @Test
    void demoValues(){
        for (TrafficLightColor color: TrafficLightColor.values()){
            System.out.println(color.toString().toLowerCase());
        }
    }


    @Test
    void demoComparisons(){
        TrafficLightColor color1 = TrafficLightColor.RED;
        TrafficLightColor color2 = TrafficLightColor.GREEN;
        // equals
        assertTrue(color1.equals(TrafficLightColor.RED));
        assertFalse(color1.equals(color2));
        assertTrue(!color1.equals(color2));
        assertEquals(color1, TrafficLightColor.RED);
        assertNotEquals(color1, color2);
        // ==
        assertTrue(color1 == TrafficLightColor.RED); // same object
        assertSame(color1, TrafficLightColor.RED);
        // order
        assertTrue(color1.compareTo(color2) > 0);
    }

    @ParameterizedTest
    @EnumSource(TrafficLightColor.class)
    void demoSwitchCase(TrafficLightColor color){
        // null case is not mandatory
        System.out.println(color);
        int delay = switch (color){
            case GREEN -> 30;
            case ORANGE -> 3;
            case RED -> 27;
        };
        System.out.println("Delay: " + delay);

        int delay2 = switch (color){
            case GREEN -> 30;
            case ORANGE -> 3;
            default -> 0; // or throw exception
        };
        System.out.println("Delay 2: " + delay2);
    }

    @ParameterizedTest
    @EnumSource(TrafficLightColor.class)
    @NullSource
    void demoSwitchCaseMayBeNull(TrafficLightColor color){
        System.out.println(color);
        int delay = switch (color){
            case GREEN -> 30;
            case ORANGE -> 3;
            case RED -> 27;
            case null -> 0; // or throw custom exception instead of NullPointerException
        };
        System.out.println("Delay: " + delay);

        int delay2 = switch (color){
            case GREEN -> 30;
            case ORANGE -> 3;
            case null, default -> 0; // or throw exception
        };
        System.out.println("Delay 2: " + delay2);
    }
}
