package iaew.tpi.apiClientes.exceptions;

import org.springframework.http.HttpStatus;

public class ReservasException extends RuntimeException{

    private HttpStatus status;

    public ReservasException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
