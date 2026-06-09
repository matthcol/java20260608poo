package demo.basics;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;

class DemoSimpleTypes {

    @Test
    void demoText(){
        System.out.println("Demo Text");
        // classic variable
        String city1 = "Pau";
        // Java 11 'var' keyword : type inference
        var city2 = "Toulouse";
        String city3;
        System.out.println("City 1: " + city1);
        System.out.println("City 2: " + city2);
        // System.out.println("City 2: " + city3); // java: variable city3 might not have been initialized
        System.out.println(MessageFormat.format(
                "From city {0} to {1}",
                city1,
                city2
        ));
        System.out.println("Length: " + city1.length());
        System.out.println("Char 0: " + city1.charAt(0)); // => char 'P'
        char character = 'ÿ';
        System.out.println("Substring: " + city2.substring(0, 5)); // => "Toulo"
        System.out.println("Uppercase : "  + city2.toUpperCase());  // see also: toLowerCase
        System.out.println("Starts with T ? " + city2.startsWith("T")); // see also: endsWith, contains
    }

    @Test
    void demoUnicode(){
        List<String> words = List.of(
                "télé", "âge", "œuvre", "façade",
                "東京",
                "I ❤ 🦜"
        );
        for (String word: words){
            System.out.println(word);
        }
    }

    @Test
    void demo8PrimitiveTypes(){
        // NB : direct addressing in memory
        // text
        char emoji = 'ÿ';
        // numeric : signed integers
        short nbPerson = 4; // 16 bits
        int nbCities = 36_000; // 32 bits
        long nbInfos = 9_000_000_000_000_000_000L; // 64 bist
        // numeric : floats IEEE754
        float temperature = 20.5F;
        double pi =  3.14159265358979323846264338327950288419716939937510; // 1D, 1E25
        // boolean
        boolean isHot = temperature > 30;
        // byte
        byte code = 0x2a;

        // numeric computing with operators
        double res = (+nbPerson + 1) * 10 * -nbCities / (-temperature - 1.0);
        System.out.println("Result: " + res);
        int r = nbCities % 57;
        System.out.println("r : " + nbPerson);
        nbPerson++;
        ++nbPerson;
        nbPerson--;
        --nbPerson;
        nbPerson += 2;  // others:  -=   *=  /=  %=
        System.out.println("Nb of persons: " + nbPerson);

        // NB: no overloads for object types (except + for String)
    }

    @Test
    void demoPrecisionFloat(){
        double price = 0.1;  // 0.1 (base 10) = 0.000110011001100100110011..
        System.out.println("One: " + price);
        System.out.println("Two: " + (2 * price));
        System.out.println("Three: " + (3 * price));
        Assertions.assertNotEquals(0.3, 3 * 0.1);
    }

    @Test
    void BigDecimal(){
        BigDecimal price = new BigDecimal("0.1");  // 0.1 (base 10) = 0.000110011001100100110011..
        System.out.println("One: " + price);
        BigDecimal res = price.multiply(new BigDecimal(2));
        System.out.println("Two: " + res);
        res = price.multiply(new BigDecimal(3));
        System.out.println("Three: " + res);
        Assertions.assertEquals(new BigDecimal("0.3"), res);
    }

    @Test
    void demoComparisons() {
        // primitives
        int nbPersons = 4;
        System.out.println(nbPersons == 4);
        System.out.println(nbPersons != 4);
        System.out.println(nbPersons < 4);
        System.out.println(nbPersons <= 4);
        System.out.println(nbPersons > 4);
        System.out.println(nbPersons >= 4);

        // objects
        String city = "Toulouse";
        System.out.println(city == "Pau"); // @ mémoire (reference)
        System.out.println(city.equals("Pau")); // sequences of characters
        System.out.println(!city.equals("Pau"));

        String city2 = "Toulouse";
        System.out.println(city == city2); // !!!! ref to the same litteral object
        System.out.println(city.toUpperCase() == city2.toUpperCase());

        // System.out.println(city < city2); // error
        System.out.println("Toulouse < Pau: " + (city.compareTo("Pau") < 0));

        // logical operators: ! (not),  && (and),  || (or)
        boolean ok = (city.length() < 20) && (nbPersons > 10);
        ok = (city.length() < 20) || (nbPersons > 10);
    }

    @Test
    void demoByteOperators(){
        // Bitwise operators : | (OR), & (AND), ~ (NOT), ^ (XOR), << (left shift), >> (right shift)
        int info = 0xf0;
        int res = info | 0x0f;
        System.out.println(res);
    }

}
