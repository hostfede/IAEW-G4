package iaew.tpi.apiClientes.reservas.clients;


/**
 * Cliente genérico para consumir las APIs de reservas, en teoría con un mismo método deberíamos ser capaces de
 * consumir las 3 APIs
* */
public interface IClienteReservasGen {

    <T> T getReserva(String baseUrl, String path, int cliente_id, Class<T> responseType);

}
