package iaew.tpi.apiClientes.exceptions;

import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class ApiError implements Serializable {

    private HttpStatus status;
    private Map<String,String> causes;
    private String message;
    private LocalDateTime timestamp;

    public ApiError(HttpStatus status, Map<String, String> causes, String message) {
        this.status = status;
        this.causes = causes;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(HttpStatus status, Map<String, String> causes) {
        this.status = status;
        this.causes = causes;
        this.timestamp = LocalDateTime.now();
    }

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String message) {
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public void addCause(String cause, String message){
        if(causes == null) causes = new HashMap<>();
        causes.put(cause, message);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Map<String, String> getCauses() {
        return causes;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp(){
        return timestamp;
    }
}
