package iaew.tpi.apiClientes.transferObjects;

public record PlanPuntosDto(
        int id,
        ClienteDto cliente,
        int puntos_acumulados,
        String nivel
) {
}
