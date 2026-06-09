package demo.lombok;

import model.lombok.CityFr;
import org.junit.jupiter.api.Test;

public class DemoCityFr {

    @Test
    void demoCityFr(){
        CityFr city = new CityFr();
        city.setName("Toulouse");
        System.out.println(city);
        System.out.println(city.getName());

        CityFr city2 = new CityFr("Toulouse");

        CityFr city3 = CityFr.builder()
                .name("Bordeaux")
                .population(300_000)
                .build();

        // new CityFr("", "", 12, 12);  // private access
    }

}
