package geometry.computing;

import geometry.model.Mesurable2D;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class GeoComputing {

    public static double totalArea(Stream<? extends Mesurable2D> mesurables){
        return mesurables.mapToDouble(Mesurable2D::area)
                .sum();
    }

    public static double totalArea(Mesurable2D... mesurables){
        return totalArea(Arrays.stream(mesurables));
    }

    public static double totalArea(Collection<? extends Mesurable2D> mesurables){
        return totalArea(mesurables.stream());
    }

    public static double totalArea(Iterable<? extends Mesurable2D> mesurables){
        return totalArea(StreamSupport.stream(mesurables.spliterator(), true));
    }

}
