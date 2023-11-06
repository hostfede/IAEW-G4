package iaew.tpi.apiClientes.reservas.transferObjects;

public record TransporteDto(int id, String tipo, String descripcion,
                            int capacidad, double precio_base) {
}
