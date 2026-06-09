package demo.classic;

import model.classic.CityFr;
import org.junit.jupiter.api.Test;

public class DemoCityFr {
    @Test
    void demoCity(){
        var city1 = new CityFr();
        var city2 = new CityFr("Toulouse");
        var city3 = new CityFr("31055", "Toulouse", 500_000);
        city1.setName("Pau");
        System.out.println(city1.getName());
        System.out.println(city1);
        System.out.println(city1.toString());
    }
}
