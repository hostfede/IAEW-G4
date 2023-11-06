package iaew.tpi.apiClientes.transferObjects;

import java.time.LocalDate;
public record ClienteDto(int id, String nombre,
                         String apellido,
                         String email,
                         String telefono,
                         LocalDate fecha_nacimiento,
                         String documento_identidad) {
}
