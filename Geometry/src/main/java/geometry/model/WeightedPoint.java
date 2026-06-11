package geometry.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeightedPoint extends Point {
    private double weight;

    public WeightedPoint() {
    }

    public WeightedPoint(String name, double x, double y, double weight) {
        super(name, x, y);
        this.weight = weight;
    }

    @Override
    public String toString() {
        return super.toString() + '#' + weight;
    }
}
