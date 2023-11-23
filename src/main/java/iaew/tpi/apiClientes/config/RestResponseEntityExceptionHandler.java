package iaew.tpi.apiClientes.config;


import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.exceptions.ApiError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {APIException.class})
    protected ResponseEntity<Object> handleDomainException(APIException ex,
                                                             WebRequest request){

        ApiError apiError = new ApiError(ex.getStatus(), ex.getCauses(), ex.getMessage());
        return handleExceptionInternal(ex, apiError, new HttpHeaders(), ex.getStatus(), request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request) {
        Class<?> type = e.getRequiredType();
        String message;
        if(type.isEnum()){
            message = "The parameter " + e.getName() + " must have a value among : ";
        }
        else{
            message = "The parameter " + e.getName() + " must be of type " + type.getTypeName();
        }
        ApiError apiError = new ApiError(HttpStatus.UNPROCESSABLE_ENTITY, message);

        return handleExceptionInternal(e,apiError,new HttpHeaders(),HttpStatus.UNPROCESSABLE_ENTITY, request);
    }
}
