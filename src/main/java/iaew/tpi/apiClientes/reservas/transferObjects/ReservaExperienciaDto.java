package iaew.tpi.apiClientes.reservas.transferObjects;

import java.time.LocalDate;
import java.util.Date;

public record ReservaExperienciaDto (int id, ExperienciaDto experiencia,
                                     LocalDate fecha, int participantes,
                                     double precio_total, String estado)
{
}
