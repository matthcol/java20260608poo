package geometry.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Form {
    // @NotBlank
    private String name;

    protected Form() {
    }

    protected Form(String name) {
        this.name = name;
    }

    public abstract void translate(double deltaX, double deltaY);
}
