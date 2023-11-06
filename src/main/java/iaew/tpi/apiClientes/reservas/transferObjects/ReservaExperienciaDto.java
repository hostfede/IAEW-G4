package iaew.tpi.apiClientes.reservas.transferObjects;

import java.util.Date;

public record ReservaExperienciaDto (int id, ExperienciaDto experiencia,
                                     Date fecha, int participantes,
                                     double precio_total, String estado)
{
}
