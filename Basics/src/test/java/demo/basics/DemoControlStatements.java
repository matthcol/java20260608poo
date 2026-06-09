package demo.basics;

import algo.Euclide;
import org.junit.jupiter.api.Test;

import java.text.MessageFormat;
import java.util.List;

public class DemoControlStatements {

    @Test
    void demo1(){
        List<String> cities = List.of("Limoges", "Montpellier", "Nice", "Pau");

        // for-each loop (Java 5) = enhanced for
        for (String city: cities){
            System.out.println(city);
        }
        System.out.println();

        // for-i : variable can be defined before
        for (int i = 0; i < cities.size(); i++) {
            String city = cities.get(i);
            System.out.println(city);
        }
        System.out.println();

        for (String city: cities){
            System.out.print(city + " : ");
            if (city.length() < 5){
                System.out.println("Less than 5 letters");
            } else if (city.length() < 10) {
                System.out.println("Less than 10 letters");
            } else {
                System.out.println("Lot of letters");
            }
        }
    }

    @Test
    void demoSwitchCaseClassic(){
        // switch ... case classic: 8 primitives types + (Java 7) String
        var cities = List.of("Limoges", "Montpellier", "Nice", "Pau");
        for (var city: cities){
            System.out.print(city + " : ");
            switch (city.length()){
                case 1:
                case 2:
                case 3:
                    System.out.println("From 1 to 3 letters");
                    break;
                case 4:
                    System.out.println("4 letters");
                case 5:
                case 6:
                    System.out.println("From 4 to 6 letters");
                    break;
                default:
                    System.out.println("7 or more letters");
            }
            System.out.println();
        }
    }

    @Test
    void demoPatternMatching(){
        var cities = List.of("Limoges", "Montpellier", "Nice", "Pau");
        // Java 21
        for (var city: cities){
            System.out.print(city + " : ");
            switch (city.length()){
                case 1, 2, 3 -> System.out.println("From 1 to 3 letters");
                case 4, 5, 6 -> System.out.println("From 4 to 6 letters");
                default -> System.out.println("7 or more letters");
            }
        }
    }

    @Test
    void demoPatternMatchingExpression(){
        // Java 21
        // https://docs.oracle.com/en/java/javase/21/language/pattern-matching.html
        var cities = List.of("Limoges", "Montpellier", "Nice", "Pau");
        for (var city: cities){
            System.out.print(city + " : ");
            int code = switch (city.length()){
                case 1, 2, 3 -> 1;
                case 4, 5, 6 -> 2;
                default -> 3; // mandatory to cover every possibility
            };
            System.out.println(code);
        }
    }

    @Test
    void demoMath(){
        double x = 45;
        double y = Math.sqrt(x);
        System.out.println(y);
    }

    // TODO: while + do while

    @Test
    void demoEuclideWhile(){
        int x = 15;
        int y = 21;
        int g = Euclide.gcd(x, y);
        System.out.println(MessageFormat.format(
                "Gcd of {0} and {1} is {2}.", x, y, g
        ));
    }

















}
