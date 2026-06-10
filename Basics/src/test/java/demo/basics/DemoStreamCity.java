package demo.basics;

import model.lombok.CityFr;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import utils.CsvTools;
import utils.converter.CityCsvConverter;

import java.text.MessageFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    // population totale des villes du département ?
    @ParameterizedTest
    @ValueSource(strings = {"06", "34", "87", "64", "31", "974", "666"})
    void demoTotalPopulationOfDepartment(String deptCode){
        int populationTotal = cityList.stream()
                .filter(city -> city.getDepartmentCode().equals(deptCode))
                .peek(System.out::println)
                .mapToInt(CityFr::getPopulation)
                .peek(System.out::println)
                .sum();
        System.out.println(MessageFormat.format(
                "Population of department {0} is {1}.",
                deptCode,
                populationTotal
        ));
    }

    @ParameterizedTest
    @ValueSource(strings = {"06", "34", "87", "64", "31", "974", "666"})
    void demoAveragePopulationOfDepartment1(String deptCode) {
        OptionalDouble optAveragePopulation = cityList.stream()
                .filter(city -> city.getDepartmentCode().equals(deptCode))
                .mapToInt(CityFr::getPopulation)
                .average();
        if (optAveragePopulation.isPresent()) {
            System.out.println(MessageFormat.format(
                    "Average population of department {0} is {1}.",
                    deptCode,
                    optAveragePopulation.getAsDouble()
            ));
        } else {
            System.out.println(MessageFormat.format(
                    "No population in department {0}.",
                    deptCode
            ));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"06", "34", "87", "64", "31", "974", "666"})
    void demoAveragePopulationOfDepartment2(String deptCode){
        double averagePopulation = cityList.stream()
                .filter(city -> city.getDepartmentCode().equals(deptCode))
                .mapToInt(CityFr::getPopulation)
                .average()
                .orElse(Double.NaN);  // see: orElseGet, orElseThrow
        System.out.println(MessageFormat.format(
                "Average population of department {0} is {1}.",
                deptCode,
                averagePopulation
        ));
    }

    @ParameterizedTest
    @ValueSource(strings = {"06", "34", "87", "64", "31", "974", "666"})
    void demoAveragePopulationOfDepartment3(String deptCode) {
        OptionalDouble optAveragePopulation = cityList.stream()
                .filter(city -> city.getDepartmentCode().equals(deptCode))
                .mapToInt(CityFr::getPopulation)
                .average();
        optAveragePopulation.ifPresentOrElse(
                averagePopulation -> System.out.println(MessageFormat.format(
                        "Average population of department {0} is {1}.",
                        deptCode,
                        averagePopulation
                )),
                () -> System.out.println(MessageFormat.format(
                        "No population in department {0}.",
                        deptCode
                ))
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"06", "34", "87", "64", "31", "974", "666"})
    void demoAveragePopulationOfDepartment4(String deptCode) {
        OptionalDouble optAveragePopulation = cityList.stream()
                .filter(city -> city.getDepartmentCode().equals(deptCode))
                .mapToInt(CityFr::getPopulation)
                .average();
        optAveragePopulation.stream()
                .mapToObj(averagePopulation -> MessageFormat.format(
                        "Average population of department {0} is {1}.",
                        deptCode,
                        averagePopulation
                ))
                // .findFirst()
                .forEach(System.out::println);
    }

    @ParameterizedTest
    @ValueSource(strings = {"06", "34", "87", "64", "31", "974", "666"})
    void demoPopulationStatisticsOfDepartment4(String deptCode) {
        IntSummaryStatistics populationStatistics = cityList.stream()
                .filter(city -> city.getDepartmentCode().equals(deptCode))
                .mapToInt(CityFr::getPopulation)
                .summaryStatistics();
        System.out.println("Stats : " + populationStatistics);
        System.out.println("Nb of cities : " + populationStatistics.getCount());
    }

    @ParameterizedTest
    @ValueSource(strings = {"06", "34", "87", "64", "31", "974", "666"})
    void demoPopulationTotalOfDepartmentCollect(String deptCode) {
        int populationTotal = cityList.stream()
                .filter(city -> city.getDepartmentCode().equals(deptCode))
                .collect(Collectors.summingInt(CityFr::getPopulation));
        System.out.println(MessageFormat.format(
                "Population of department {0} is {1}.",
                deptCode,
                populationTotal
        ));
    }

    //  toCollection + joining + groupby
    @ParameterizedTest
    @ValueSource(strings = {"06", "34", "87", "64", "31", "974", "666"})
    void demoPopulationTotalOfDepartmentReduce(String deptCode) {
        int populationTotal = cityList.stream()
                .filter(city -> city.getDepartmentCode().equals(deptCode))
                .mapToInt(CityFr::getPopulation)
                .reduce(0, (pop1, pop2) -> pop1 + pop2);
                // .reduce(0, Integer::sum);
        System.out.println(MessageFormat.format(
                "Population of department {0} is {1}.",
                deptCode,
                populationTotal
        ));
    }

    // collection des villes de plus de ? d'habitants
    @Test
    void demoTop10() {
        List<CityFr> top10 = cityList.stream()
                .sorted(Comparator.comparingInt(CityFr::getPopulation).reversed()) // !! cost n*log(n)
                .limit(10)
                .toList();
        top10.forEach(System.out::println);
    }

    @ParameterizedTest
    @ValueSource(ints = {50_000, 100_000, 500_000, 1_000_000, 5_000_000})
    void demoCityPopulationThresholdToList(int populationThreshold){
        List<CityFr> filteredCities = cityList.stream()
                .filter(city -> city.getPopulation() >= populationThreshold)
                .toList();
        filteredCities.forEach(System.out::println);
        System.out.println(filteredCities.getClass());
    }


    // version 2 with TreeSet (name only)
    // version 3 with TreeSet (city)

    @ParameterizedTest
    @ValueSource(ints = {50_000, 100_000, 500_000, 1_000_000, 5_000_000})
    void demoCityPopulationThresholdToSet(int populationThreshold) {
        Set<CityFr> filteredCities = cityList.parallelStream()
                .filter(city -> city.getPopulation() >= populationThreshold)
//                .collect(Collectors.toSet());
//                .collect(Collectors.toCollection(() -> new HashSet<>()));
                .collect(Collectors.toCollection(HashSet::new));
        filteredCities.forEach(System.out::println);
        System.out.println(filteredCities.getClass());
    }

    @ParameterizedTest
    @ValueSource(ints = {50_000, 100_000, 500_000, 1_000_000, 5_000_000})
    void demoCityPopulationThresholdToSortedSetNames(int populationThreshold) {
        SortedSet<String> cityNames = cityList.parallelStream()
                .filter(city -> city.getPopulation() >= populationThreshold)
                .map(CityFr::getName)
                .collect(Collectors.toCollection(TreeSet::new)); // natural order String
        // !! be careful with homonyms => a set has no doubles
        System.out.println(cityNames);
    }

    @ParameterizedTest
    @ValueSource(ints = {50_000, 100_000, 500_000, 1_000_000, 5_000_000})
    void demoCityPopulationThresholdToTreeSetPopAsc(int populationThreshold) {
        Set<CityFr> filteredCities = cityList.parallelStream()
                .filter(city -> city.getPopulation() >= populationThreshold)
//                .collect(Collectors.toCollection(TreeSet::new));  // cannot be cast to class java.lang.Comparable
                .collect(Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparingInt(CityFr::getPopulation)
                                .thenComparing(CityFr::getCodeInsee)  // distinguish cities with identical population
                )));
        filteredCities.forEach(System.out::println);
        System.out.println(filteredCities.getClass());
    }

    @ParameterizedTest
    @ValueSource(ints = {10_000})
    void demoCityPopulationThresholdToTreeSetDeptAscPopDesc(int populationThreshold) {
        Set<CityFr> filteredCities = cityList.parallelStream()
                .filter(city -> city.getPopulation() >= populationThreshold)
//                .collect(Collectors.toCollection(TreeSet::new));  // cannot be cast to class java.lang.Comparable
                .collect(Collectors.toCollection(
                        () -> new TreeSet<>(
                                Comparator.comparing(CityFr::getDepartmentCode, String::compareToIgnoreCase) // NB: replace compareToIgnoreCase by custom dept order
                                        .thenComparing(CityFr::getPopulation, Comparator.reverseOrder()) // NB: improve using primitives
                        )
                ));
        filteredCities.forEach(System.out::println);
        System.out.println(filteredCities.getClass());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 10})
    void demoCityPopulationUnderThresholdToTreeSet(int populationThreshold) {
        SortedSet<CityFr> filteredCities = cityList.parallelStream()
                .filter(city -> city.getPopulation() <= populationThreshold)
                .collect(Collectors.toCollection(() -> new TreeSet<>(
                        Comparator.comparingInt(CityFr::getPopulation)
                                .thenComparing(CityFr::getCodeInsee)  // distinguish cities with identical population
                )));
        filteredCities.forEach(System.out::println);
        System.out.println(filteredCities.getClass());
    }

    @Test
    void demoGroupBy(){
        Map<String, List<CityFr>> result = cityList.stream()
                .filter(cityFr -> cityFr.getAverageAltitude() < 50)
                .collect(Collectors.groupingBy(CityFr::getDepartmentCode));
        System.out.println(result.getClass()); // no guarantee given by the specification

        System.out.println();
        for (String dept: result.keySet()){
            System.out.println(dept);
        }

        System.out.println();
        for (List<CityFr> cityDeptList: result.values()){
            System.out.println(cityDeptList);
        }

        System.out.println();
        for (Map.Entry<String, List<CityFr>> pairDeptCityList: result.entrySet()){
            String dept = pairDeptCityList.getKey();
            List<CityFr> cityDeptList = pairDeptCityList.getValue();
            System.out.println("* Department: " + dept);
            cityDeptList.forEach(city -> System.out.println("    - " + city));
        }

        System.out.println();
        result.forEach((dept, cityDeptList) -> {
            System.out.println("# Department: " + dept);
            cityDeptList.forEach(city -> System.out.println("    ~ " + city));
        });
    }

    @Test
    void demoGroupBySortedMap(){
        Map<String, List<CityFr>> result = cityList.stream()
                .filter(cityFr -> cityFr.getAverageAltitude() < 50)
                .collect(Collectors.groupingBy(
                        CityFr::getDepartmentCode,
                        TreeMap::new,
                        Collectors.toList()
                ));

        System.out.println(result.getClass());

        System.out.println();
        result.forEach((dept, cityDeptList) -> {
            System.out.println("# Department: " + dept);
            cityDeptList.forEach(city -> System.out.println(
                    MessageFormat.format(
                        "    ~ {0}, average altitude : {1}",
                        city.getName(),
                        city.getAverageAltitude()
                    )
            ));
        });
    }



}
