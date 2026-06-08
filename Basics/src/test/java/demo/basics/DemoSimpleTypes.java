package demo.basics;


import org.junit.jupiter.api.Test;

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
}
