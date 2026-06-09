package demo.records;

import model.records.CityFr;
import org.junit.jupiter.api.Test;

public class DemoCityFr {

    @Test
    void demoCityFr(){
        CityFr city = new CityFr("31055", "Toulouse", 500_000, 120);
        System.out.println(city);
        System.out.println(city.area());
    }
}
