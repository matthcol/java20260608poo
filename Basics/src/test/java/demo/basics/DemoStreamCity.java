package demo.basics;

import model.lombok.CityFr;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.CsvTools;
import utils.converter.CityCsvConverter;

import java.util.List;

public class DemoStreamCity {
    static List<CityFr> cityList;

    @BeforeAll
    static void loadCities(){
        String csvResource = "/communes-france-2025.csv";
        var mapper = CsvTools.headersToMapper(CsvTools.headers(csvResource));
        cityList = CsvTools.streamFromCsv(csvResource)
                .map(line -> CityCsvConverter.lineCsvToCity(line, mapper))
                .toList(); // shortcut Java 17
    }

    @Test
    void demoSample(){
        cityList.stream()
                .skip(15_000)
                .limit(10)
                .peek(System.out::println)
                .forEach(System.out::println);
    }

    @Test
    void demoCountCityWithPopulation100K(){
        long nbCity = cityList.stream()
                .filter(city -> city.getPopulation() >= 100_000)
                .count();
        System.out.println(nbCity);

    }
}
