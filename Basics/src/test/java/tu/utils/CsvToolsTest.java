package tu.utils;

import org.junit.jupiter.api.Test;
import utils.CsvTools;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class CsvToolsTest {

    @Test
    void testHeaders_whenPresent() {
        String[] headers = CsvTools.headers(
                "data_with_headers_comma_quote_utf8.csv",
                ',',
                '"',
                StandardCharsets.UTF_8
        );
        assertAll(
                () -> assertEquals("nom", headers[0]),
                () -> assertEquals("code postal", headers[1]),
                () -> assertEquals("cœur, €", headers[2])
        );
    }

    @Test
    void testHeaders_withDefaults_whenPresent() {
        String[] headers = CsvTools.headers(
                "data_with_headers_comma_quote_utf8.csv"
        );
        assertAll(
                () -> assertEquals("nom", headers[0]),
                () -> assertEquals("code postal", headers[1]),
                () -> assertEquals("cœur, €", headers[2])
        );
    }
}