package iaew.tpi.apiClientes.reservas.transferObjects;

public record ExperienciaDto(int id, String titulo, String descripcion,
                             String ubicacion, float duracion,
                             double precio, String inclusiones,
                             String restricciones)
{
}
