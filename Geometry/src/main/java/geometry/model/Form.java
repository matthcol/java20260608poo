package geometry.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Form {
    private String name;

    protected Form() {
    }

    protected Form(String name) {
        this.name = name;
    }

    public abstract void translate(double deltaX, double deltaY);
}
