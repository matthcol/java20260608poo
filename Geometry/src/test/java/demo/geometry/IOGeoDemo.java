package demo.geometry;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.Stream;

public class IOGeoDemo {

    @Test
    void readInputStream() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.bin");
        byte[] buffer = new byte[256];
        int byteReadCount = in.read(buffer);
        in.close();;
        System.out.println(Arrays.toString(buffer));
        System.out.println("Byte(s) read: " + byteReadCount);
    }

    @Test
    void readText() throws IOException {
        InputStream in = new FileInputStream("src/test/resources/data.bin");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String firstLine = reader.readLine();
        reader.close();
        System.out.println(firstLine);
    }

    @Test
    void readTryCatchFinally() throws IOException {
        // until Java 7
        InputStream in = null;
        BufferedReader reader = null;
        try {
            in = new FileInputStream("src/test/resources/data.bin");
            reader = new BufferedReader(new InputStreamReader(in));
            String firstLine = reader.readLine();
            System.out.println(firstLine);
        } catch (IOException exc) {
            throw exc;
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException exc) {
                throw exc;
            } finally {
                if (in != null) in.close();
            }
        }
    }

    @Test
    void demoRead_withTryWithResources_throwingException() throws IOException {
        // since Java 7
        String firstLine = null;
        try (
                InputStream in = new FileInputStream("src/test/resources/data.bin");
                Reader reader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(reader);
        ){
            firstLine = bufferedReader.readLine();
        } // try auto-close 3 resources
        System.out.println(firstLine);
    }

    @ParameterizedTest
    @ValueSource(strings={
            "src/test/resources/data.bin",
            "src/test/resources/data_utf8.txt",
            "src/test/resources/data_win1252.txt",
            "src/test/resources/file_does_not_exists.bin",
    })
    void demoRead_withTryWithResources_catchingException(String filename) {
        // since Java 7
        try {
            // BEGIN USE CASE
            String firstLine = null;
            try (
                    InputStream in = new FileInputStream(filename);
                    Reader reader = new InputStreamReader(in);
                    BufferedReader bufferedReader = new BufferedReader(reader);
            ) {
                firstLine = bufferedReader.readLine();
            } // try auto-close 3 resources
            System.out.println("Data read: " + firstLine);
            // END USE CASE
        } catch (IOException exc) {
            System.out.println("[ERROR] error while reading file: " + exc.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings={
            "src/test/resources/data.bin",
            "src/test/resources/data_utf8.txt",
            "src/test/resources/data_win1252.txt",
            "src/test/resources/file_does_not_exists.bin",
    })
    void demoRead_withTryWithResources_rethrowRuntime(String filename) {
        // BEGIN USE CASE
        String firstLine = null;
        try (
                InputStream in = new FileInputStream(filename);
                Reader reader = new InputStreamReader(in);
                BufferedReader bufferedReader = new BufferedReader(reader);
        ) {
            firstLine = bufferedReader.readLine();
        } catch (IOException exc){
            throw new RuntimeException("Error while processing file", exc);
        }// try auto-close 3 resources
        System.out.println("Data read: " + firstLine);
    }

    @ParameterizedTest
    @ValueSource(strings={
            "src/test/resources/data_utf8.txt",
            "src/test/resources/file_does_not_exists.bin",
            "src/test/resources/"
    })
    void demoJava_IO(String filename){
        File file = new File(filename);
        if (file.exists()) {
            if (file.isFile()) {
                System.out.println("Existing file: " + file);
                // TODO: read file
            } else if (file.isDirectory()) {
                System.out.println("Existing directory: " + file);
                // TODO: read each file in this directory
                // file.listFiles()  => File[]
            } else {
                System.out.println("Not a regular file or directory");
            }
        } else {
            System.out.println("File or directory not found");
        }
    }

    @ParameterizedTest
    @ValueSource(strings={
            "src/test/resources/data_utf8.txt",
            "src/test/resources/file_does_not_exists.bin",
            "src/test/resources/"
    })
    void demoJava_NIO(String filename) throws IOException {
        Path path = Path.of(filename);
        System.out.println(path);
        if (Files.exists(path)) {
            if (Files.isRegularFile(path)) {
                System.out.println("Existing file: " + path);
                // read with a buffered reader
                try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                    System.out.println(reader.readLine());
                } // auto-close
                // short
                try (Stream<String> stream = Files.lines(path)) {
                    // TODO: add transformation + filter
                    stream.forEach(System.out::println);
                } // auto-close when stream is close

            } else if (Files.isDirectory(path)) {
                System.out.println("Existing directory : " + path);
                try (Stream<Path> stream = Files.walk(path)) {
                    stream.forEach(pathChild -> System.out.println(" - " + pathChild));
                } // auto-close on the directory

            }
        }

        File file = path.toFile();
        Path path2 = file.toPath();
        // NB: see URI + Resource
    }
}
