package br.dev.diego.services.exceptions;

public class ServerJdbcException extends RuntimeException {

    public ServerJdbcException(String message) {
        super(message);
    }

    public ServerJdbcException(String message, Throwable cause) {
        super(message, cause);
    }

}
