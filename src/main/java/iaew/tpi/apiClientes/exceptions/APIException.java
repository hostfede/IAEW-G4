package iaew.tpi.apiClientes.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class APIException extends RuntimeException{

    private Map<String, String> causes;
    private HttpStatus status;


    public APIException(String message, Map<String, String> causes, HttpStatus status) {
        super(message);
        this.causes = causes;
        this.status = status;
    }
}
