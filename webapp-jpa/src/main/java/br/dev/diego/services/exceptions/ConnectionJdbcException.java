package br.dev.diego.services.exceptions;

public class ConnectionJdbcException extends RuntimeException {

    public ConnectionJdbcException(String message) {
        super(message);
    }

    public ConnectionJdbcException(String message, Throwable cause) {
        super(message, cause);
    }

}
