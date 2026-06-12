package org.example.cinema.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
//@Retention(RetentionPolicy.SOURCE) // compiler + preprocessor
@Retention(RetentionPolicy.RUNTIME) // kept in bytecode => framework
public @interface Note {
    String value() default "";
    Level level() default Level.INFO;

    enum Level{
        CRITICAL, IMPORTANT, INFO, ANECDOTIC
    }
}
