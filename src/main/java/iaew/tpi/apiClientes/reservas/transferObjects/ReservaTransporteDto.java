package iaew.tpi.apiClientes.reservas.transferObjects;

import java.time.LocalDate;

public record ReservaTransporteDto(int id, TransporteDto transporte, LocalDate fecha_inicio,
                                   LocalDate fecha_fin, double precio_total, String estado) {
}
