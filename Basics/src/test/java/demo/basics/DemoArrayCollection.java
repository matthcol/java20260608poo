package demo.basics;

import model.dummy.NotComparableDummy;
import org.junit.jupiter.api.Test;

import java.text.Collator;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DemoArrayCollection {

    @Test
    void demoArray(){
        // static array
        double[] temperatures = {20.5, 27.5, 12.3, 33.4};

        // dynamic array
        double[] temperatures2 = new double[temperatures.length];

        System.out.println(temperatures); // [D@74f6c5d8
        System.out.println(Arrays.toString(temperatures)); // [20.5, 27.5, 12.3, 33.4]

        for (double t: temperatures){
            System.out.println(t);
        }

        for (int i = 0; i < temperatures.length; i++) {
            temperatures2[i] = temperatures[i] * 2.0 + 1.0;
        }
        System.out.println(Arrays.toString(temperatures2));

        Arrays.sort(temperatures);
        System.out.println(Arrays.toString(temperatures));
    }

    @Test
    void demoList(){
        // NB: Java 11 List.of
        List<String> cities = List.of("Nice", "Montpellier", "Limoges", "Pau", "Toulouse");
        System.out.println(cities); // [Nice, Montpellier, Limoges, Pau, Toulouse]
        System.out.println(cities.getClass()); // java.util.ImmutableCollections$ListN

        System.out.println();
        for (String city: cities){
            System.out.println(city);
        }

        System.out.println();
        for (int i = 0; i < cities.size(); i++) {
            String city = cities.get(i);
            System.out.println(city);
        }

        System.out.println();
        cities.forEach(System.out::println);

        System.out.println();
        cities.forEach(city -> System.out.println(" - City: " + city));

//        cities.add("Paris"); // UnsupportedOperationException
//        cities.set(0, "Bordeaux"); // UnsupportedOperationException

        List<String> mutableCities = new ArrayList<>(cities);
        mutableCities.add("Paris");
        mutableCities.set(3, "Bordeaux");
        Collections.addAll(mutableCities, "Pau", "Lyon", "Lille");

        List<String> otherCities = List.of("Rennes", "Marseille", "Bayonne");
        mutableCities.addAll(otherCities);
        // see also: remove, clear
        System.out.println(mutableCities);

        // Sorting list inplace
        Collections.sort(mutableCities); // uses natural order of type String (method compareTo)
        System.out.println(mutableCities);

        Collections.shuffle(mutableCities);
        System.out.println(mutableCities);

        NavigableSet<String> citySet = new TreeSet<>(mutableCities); // natural order of type String
        System.out.println(citySet);
        assertTrue(citySet.add("Grenoble"));
        assertFalse(citySet.add("Pau"));
        System.out.println(citySet);
    }

    @Test
    void demoMutableCollection(){
        // List<String> cityList = new ArrayList<>(1_000_000_000);
        // List cityList = new ArrayList(); // Java 1.2
        // List<String> cityList = new ArrayList<String>(); // Java 5
         List<String> cityList = new ArrayList<>(); // inference <> => <String>  (Java 7)
        // var cityList = new ArrayList<String>();                                 (Java 11)

        Collections.addAll(cityList,
                "Rennes", "Bordeaux", "Toulouse", "Nice", "Paris",
                "Lille", "Montpellier", "Pau", "Lyon", "Marseille", "Limoges", "Bayonne"
        );
        System.out.println(cityList);
    }

    @Test
    void demoSortCollator(){
        List<String> wordsFr = new ArrayList<>();
        Collections.addAll(
                wordsFr,
                "télé", "âge", "œuvre", "Façade", "Zèbre", "arbre",
                "ode", "offre", "face",
                "été", "étuve", "étage"
        );
        Collections.sort(wordsFr); // natural order
        System.out.println(wordsFr);
        // wordsFr.sort((w1, w2) -> -1);
        // wordsFr.sort((w1, w2) -> w1.compareToIgnoreCase(w2));
        wordsFr.sort(String::compareToIgnoreCase);
        System.out.println(wordsFr);

        wordsFr.sort(Collator.getInstance());
        System.out.println(wordsFr);

//        Collator collatorFr = Collator.getInstance(Locale.FRENCH);
        Collator collatorFr = Collator.getInstance(Locale.of("fr", "FR"));
        Collator collatorEs = Collator.getInstance(Locale.of("es", "ES"));

        List<String> wordsEs = new ArrayList<>();
        Collections.addAll(wordsEs, "mano", "mañana", "matador");
        wordsEs.sort(collatorFr);           // [mañana, mano, matador]
        System.out.println(wordsEs);
        wordsEs.sort(collatorEs);
        System.out.println(wordsEs);       // [mano, mañana, matador]
    }

    @Test
    void demoNotComparable(){
        List<NotComparableDummy> dummies = new ArrayList<>();
        // Collections.sort(dummies); // compilation error : NotComparableDummy is not Comparable
        NavigableSet<NotComparableDummy> dummySet = new TreeSet<>();
        // dummySet.add(new NotComparableDummy()); // java.lang.ClassCastException: class model.dummy.NotComparableDummy cannot be cast to class java.lang.Comparable
        NavigableSet<NotComparableDummy> dummySet2 = new TreeSet<>(Comparator.comparing(Objects::hashCode));
        Collections.addAll(dummySet2, new NotComparableDummy(), new NotComparableDummy(), new NotComparableDummy());
        System.out.println(dummySet2);
        dummySet2.forEach(d -> System.out.println(d.hashCode()));
    }
}
