package iaew.tpi.apiClientes.reservas.clients;


import java.util.List;

/**
 * Cliente genérico para consumir las APIs de reservas, en teoría con un mismo método deberíamos ser capaces de
 * consumir las 3 APIs
* */
public interface IClienteReservasGen {

    <T> T getReserva(String baseUrl, String path, Integer cliente_id, Class<T> responseType);

    <T> List<T> getReservas(String baseUrl, String path, Integer cliente_id, Class<T> responseType);

}
