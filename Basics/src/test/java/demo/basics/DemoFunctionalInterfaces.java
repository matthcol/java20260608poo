package demo.basics;

import model.lombok.CityFr;
import org.junit.jupiter.api.Test;
import utils.function.TriFunction;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;
import java.util.function.*;

public class DemoFunctionalInterfaces {
    @Test
    void demoTypes(){
        // Function : CityFr -> String
        Function<CityFr, String> f1 = CityFr::getName;
        Function<CityFr, String> f2 = cityFr -> cityFr.getName().toUpperCase();

        // Predicate : CityFr -> boolean
        Predicate<CityFr> p1 = cityFr -> cityFr.getPopulation() >= 1_000_000;
        Predicate<String> p2 = String::isBlank;
        Predicate<String> p3 = text -> text.startsWith("T");

        // Supplier : () -> CityFr
        Supplier<CityFr> s1 = CityFr::new; // default constructor
        Supplier<CityFr> s2 = () -> new CityFr("Pau");

        // Consumer : CityFr -> void (or result ignored)
        Consumer<CityFr> c1 = System.out::println;
        Consumer<CityFr> c2 = cityFr -> cityFr.setPopulation(0);

        // Comparator : CityFr x CityFr -> int
        Comparator<CityFr> cmp1 = Comparator.comparing(CityFr::getDepartmentCode)
                .thenComparing(CityFr::getCodeInsee);
        Comparator<String> cmp2 = String::compareTo;
        Comparator<String> cmp3 = String::compareToIgnoreCase;
        Comparator<String> cmp4 = Collator.getInstance(Locale.FRENCH)::compare;

        // BinaryOperator : String x String -> String
        BinaryOperator<String> op1 = (text1, text2) -> text1 + text1;
        BinaryOperator<String> op2 = String::concat;
    }

    @Test
    void demoSyntaxLamba(){
        IntSupplier f0 = () -> 1;

        IntUnaryOperator f1 = x -> x * x + 1;
        IntUnaryOperator f1b = (int x) -> x * x + 1;
        IntUnaryOperator f1c = (var x) -> x * x + 1;

        DoubleBinaryOperator f2 = (x, y) -> (x + y) * (x - y);
        DoubleBinaryOperator f2b = (double x, double y) -> (x + y) * (x - y);
        DoubleBinaryOperator f2c = (var x, var y) -> (x + y) * (x - y);

        // String x String x String -> CityFr
        TriFunction<String, String, String, CityFr> f3 = (name, inseeCode, deptCode) -> CityFr.builder()
                .name(name)
                .codeInsee(inseeCode)
                .departmentCode(deptCode)
                .build();
    }
}
