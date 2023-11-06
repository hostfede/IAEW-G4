package iaew.tpi.apiClientes.reservas.transferObjects;

public record AlojamientoDto(int id, String nombre, String ubicacion,
                             String descripcion, String tipo,
                             double precio_por_noche,
                             int capacidad, String facilidades) {
}
