package iaew.tpi.apiClientes.transferObjects;

import iaew.tpi.apiClientes.exceptions.APIException;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public record PlanPuntosDto(
        Integer id,
        Integer puntosAcumulados,
        String nivel
) {
    public boolean validar(){

        Map<String, String > causas = new HashMap<>();
        if(puntosAcumulados == null || puntosAcumulados < 0){
            causas.put("PUNTOS", "La cantidad de puntos no puede ser negativa");
        }
        if(nivel.isEmpty() || nivel.length() > 60){
            causas.put("NIVEL", "El nivel debe contener entre 1 y 60 caracteres");
        }
        if(!causas.isEmpty()){
            throw new APIException("El plan de puntos proporcionado no contiene el formato correcto", causas, HttpStatus.BAD_REQUEST);
        }
        return true;
    }
}
