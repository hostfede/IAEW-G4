package iaew.tpi.apiClientes.reservas.transferObjects;


import java.time.LocalDate;

public record ReservaAlojamientoDto(int id, AlojamientoDto alojamiento, LocalDate fecha_entrada,
                                    LocalDate fecha_salida, int huespedes, double precio_total,
                                    String estado) {
}
