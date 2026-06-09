package utils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CsvTools {

    /**
     * Read a CSV file
     * @param filename source file with a CSV format
     * @param separator separator
     * @param quotechar quotechar protecting information containing the separator
     * @param skipLines number of lines to skip with headers or other meta-data
     * @param charset charset of the CSV file
     * @return a stream of lines decomposed
     * @throws CsvParseStreamException when an IOException or CsvParseException happens internally
     */
    public static Stream<String[]> streamFromCsv(String filename, char separator, char quotechar, int skipLines, Charset charset){
        var is = CsvTools.class.getResourceAsStream(filename);
        if (is == null) throw new CsvParseStreamException(new IOException("Resource not found: " + filename));
        Reader reader = new InputStreamReader(is, charset);
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(skipLines)
                .withCSVParser(
                        new CSVParserBuilder()
                                .withSeparator(separator)
                                .withQuoteChar(quotechar)
                                .build()
                )
                .build();

        return StreamSupport.stream(
                        Spliterators.spliteratorUnknownSize(
                                csvReader.iterator(),
                                Spliterator.ORDERED
                        ),
                        false
                )
                .onClose(() -> {
                    try {
                        System.out.println("Try close file resource");
                        csvReader.close();
                        reader.close();
                        System.out.println("File resource closed");
                    } catch (IOException e){
                        throw new CsvParseStreamException(e);
                    }
                });
    }

    /**
     * Read a CSV file with default charset UTF-8
     * @param filename source file with a CSV format
     * @param separator separator
     * @param quotechar quotechar protecting information containing the separator
     * @param skipLines number of lines to skip with headers or other meta-data
     * @return a stream of lines decomposed
     * @throws CsvParseStreamException when an IOException or CsvParseException happens internally
    */
    public static Stream<String[]> streamFromCsv(
            String filename, char separator, char quotechar, int skipLines
    ){
        return streamFromCsv(filename, separator, quotechar, skipLines, StandardCharsets.UTF_8);
    }

    /**
     * Read a CSV file with separator ',', quotechar '"' and charset UTF-8.
     * Headers are considered to be present on 1st line.
     *
     * @param filename source file with a CSV format
     * @return a stream of lines decomposed
     * @throws CsvParseStreamException when an IOException or CsvParseException happens internally
     */
    public static Stream<String[]> streamFromCsv(
            String filename
    ){
        return streamFromCsv(filename, ',', '"', 1, StandardCharsets.UTF_8);
    }

    /**
     * Read a TSV file with separator '\t' and charset UTF-8.
     * Headers are considered to be present on 1st line.
     *
     * @param filename source file with a TSV format
     * @return a stream of lines decomposed
     * @throws CsvParseStreamException when an IOException or CsvParseException happens internally
     */
    public static Stream<String[]> streamFromTsv(
            String filename
    ){
        return streamFromCsv(filename, '\t', CSVParser.NULL_CHARACTER, 1);
    }

    /**
     * Read a CSV file
     * @param filename source file with a CSV format
     * @param separator separator
     * @param quotechar quotechar protecting information containing the separator
     * @param skipLines number of lines to skip with headers or other meta-data
     * @param charset charset of the CSV file
     * @return a list of lines decomposed
     * @throws CsvParseStreamException when an IOException or CsvParseException happens internally
     */
    public static List<String[]> listFromCsv(String filename, char separator, char quotechar, int skipLines, Charset charset){
        return streamFromCsv(filename, separator, quotechar, skipLines, charset)
                .toList();
    }

    /**
     * Read a CSV file with default charset
     * @param filename source file with a CSV format
     * @param separator separator
     * @param quotechar quotechar protecting information containing the separator
     * @param skipLines number of lines to skip with headers or other meta-data
     * @return a list of lines decomposed
     * @throws CsvParseStreamException when an IOException or CsvParseException happens internally
     */
    public static List<String[]> listFromCsv(
            String filename, char separator, char quotechar, int skipLines
    ){
        return streamFromCsv(filename, separator, quotechar, skipLines).toList();
    }

    /**
     * Read a CSV file with separator ',', quotechar '"' and charset UTF-8.
     * Headers are considered to be present on 1st line.
     *
     * @param filename source file with a CSV format
     * @return a list of lines decomposed
     * @throws CsvParseStreamException when an IOException or CsvParseException happens internally
     */
    public static List<String[]> listFromCsv(
            String filename
    ){
        return streamFromCsv(filename).toList();
    }

    /**
     * Read a TSV file with separator '\t' and charset UTF-8.
     * Headers are considered to be present on 1st line.
     *
     * @param filename source file with a CSV format
     * @return a list of lines decomposed
     * @throws CsvParseStreamException when an IOException or CsvParseException happens internally
     */
    public static List<String[]> listFromTsv(
            String filename
    ){
        return streamFromTsv(filename).toList();
    }
}
