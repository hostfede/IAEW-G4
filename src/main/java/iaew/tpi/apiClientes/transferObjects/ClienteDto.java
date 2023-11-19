package iaew.tpi.apiClientes.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;
import iaew.tpi.apiClientes.exceptions.APIException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
public class ClienteDto{
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        private Integer id;
        private String nombre;
        private String apellido;
        private String email;
        private String telefono;
        private LocalDate fechaNacimiento;
        private String documentoIdentidad;

        private PlanPuntosDto planPuntos;

        public boolean validar(){

                Map<String,String> causas = new HashMap<>();
                if(nombre.isEmpty() || nombre.length() > 200){
                        causas.put("NOMBRE", "El nombre del cliente no puede estar vacío ni superar los 200 caracteres");
                }
                if(apellido.isEmpty() || apellido.length() > 200){
                        causas.put("APELLIDO", "El apellido del cliente no puede estar vacío ni superar los 200 caracteres");
                }

                if(! documentoIdentidad.matches("^(\\d{7,"+8+"})$")){
                        causas.put("NRO_DOCuMENTO", "El número de documento proporcionado no tiene el formato correcto");
                }
                if(telefono != null && !telefono.matches("^(\\d{9,13})$")){
                        causas.put("TELEFONO", "El número de teléfono ingresado no tiene el formate correcto");
                }
                if(!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")){
                        causas.put("EMAIL", "El email ingresado no posee un formato válido");
                }
                if(!fechaNacimiento.isBefore(LocalDate.now())){
                        causas.put("FECHA_ALTA", "La fecha de nacimiento no puede ser igual o posterior a la fecha actual");
                }

                if( planPuntos != null){
                        try{
                                planPuntos.validar();
                        }catch (APIException e){
                                causas.putAll(e.getCauses());
                        }
                }
                if(!causas.isEmpty()){
                        APIException apiException = new APIException("Ocurrió uno o más errores al validar el cliente", causas, HttpStatus.BAD_REQUEST);
                        throw apiException;
                }

                return true;
        }
}
