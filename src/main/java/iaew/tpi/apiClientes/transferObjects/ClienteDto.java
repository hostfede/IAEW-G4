package iaew.tpi.apiClientes.transferObjects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record ClienteDto(
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        Integer id,
        String nombre,
        String apellido,
        String email,
        String telefono,
        LocalDate fecha_nacimiento,
        String documento_identidad
) {
}
