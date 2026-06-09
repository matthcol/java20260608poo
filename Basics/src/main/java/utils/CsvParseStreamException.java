package utils;

public class CsvParseStreamException extends RuntimeException {
    public CsvParseStreamException() {
    }

    public CsvParseStreamException(String message) {
        super(message);
    }

    public CsvParseStreamException(String message, Throwable cause) {
        super(message, cause);
    }

    public CsvParseStreamException(Throwable cause) {
        super(cause);
    }
}
