package demo.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import utils.CsvTools;

import java.io.IOException;

public class DemoException {

    @Test
    void demoHierarchyRuntime(){
        var exc = new IllegalArgumentException("a must be strictly positive");
        System.out.println(exc.getClass());
        Assertions.assertTrue(exc.getClass() == IllegalArgumentException.class);
        // LSP substitution principal
        Assertions.assertTrue(exc instanceof IllegalArgumentException);
        Assertions.assertTrue(exc instanceof RuntimeException);
        Assertions.assertTrue(exc instanceof Exception);
        Assertions.assertTrue(exc instanceof Throwable);
        Assertions.assertTrue(exc instanceof Object);
        throw exc;
    }

    @Test
    void demoNotARuntimeException() throws IOException {
        IOException exc = new IOException("Error while reading file");
        Exception ref = exc;
        Assertions.assertTrue(exc instanceof Exception);
        // Assertions.assertFalse(exc instanceof RuntimeException); // compilation error
        Assertions.assertFalse(ref instanceof RuntimeException);
        throw exc; // mandatory : clause throws or try catch
    }

    @Test
    void demoReadCsvWithException(){
        var data = CsvTools.listFromCsv("/unknown-user.csv");
        System.out.println(data);
    }
}
