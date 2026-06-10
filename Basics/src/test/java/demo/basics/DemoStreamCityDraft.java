package demo.basics;

import model.lombok.CityFr;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utils.CsvTools;
import utils.converter.CityCsvConverter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DemoStreamCityDraft {

    @ParameterizedTest
    @CsvFileSource(
            resources = "/communes-france-2025.csv",
            numLinesToSkip = 34930
    )
    void demoEach(int index, String codeInsee, String name){

    }

    @Test
    void demoReadCsv(){
        List<String[]> cities = CsvTools.listFromCsv("/communes-france-2025.csv");

        // 1st data
        System.out.println(Arrays.toString(cities.get(0)));

        // sample
        System.out.println();
        cities.stream()
                .skip(17_000)
                .limit(10)
                .forEach(line -> System.out.println(Arrays.toString(line)));

        System.out.println();
        for (int i = 17_000; i < 17_010 ; i++) {
            String[] line = cities.get(i);
            System.out.println(Arrays.toString(line));
        }

        System.out.println();
        cities.stream()
                .skip(17_000)
                .limit(10)
                .map(line -> line[2])
                .forEach(System.out::println);
    }

    @Test
    void demoReadCityWithHeaders(){
        String[] headers = CsvTools.headers("/communes-france-2025.csv");
        List<String[]> data = CsvTools.listFromCsv("/communes-france-2025.csv");
        System.out.println(Arrays.toString(headers));

        String[] firstLine = data.getFirst();

//        Map<String, Integer> mapper = Map.of(
//                "nom_standard", 2,
//                "code_insee", 1
//        );

//        Map<String, Integer> mapper = new TreeMap<>();
//        for (int i = 0; i < headers.length; i++) {
//            mapper.put(headers[i], i);
//        }
        var mapper = CsvTools.headersToMapper(headers);
        System.out.println(mapper);


        // convert this line into CityFr object
//        var city = CityFr.builder()
//                .name(firstLine[mapper.get("nom_standard")])
//                .codeInsee(firstLine[mapper.get("code_insee")])
//                .population(Integer.parseInt(firstLine[mapper.get("population")]))
//                .area(Integer.parseInt(firstLine[mapper.get("superficie_km2")]))
//                .build();
        var city = CityCsvConverter.lineCsvToCity(firstLine, mapper);
        System.out.println(city);
        System.out.println(city.getArea());
    }

    @Test
    void demoReadCities(){
        String csvResource = "/communes-france-2025.csv";
        var mapper = CsvTools.headersToMapper(CsvTools.headers(csvResource));
        List<CityFr> cityList = CsvTools.streamFromCsv(csvResource)
                .map(line -> CityCsvConverter.lineCsvToCity(line, mapper))
                .toList(); // shortcut Java 17

        cityList.stream()
                .skip(20_000)
                .limit(10)
                .forEach(System.out::println);

    }


}
