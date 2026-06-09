package demo.basics;

import model.classic.CityFr;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import utils.CsvTools;

import java.util.Arrays;
import java.util.List;

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




}
