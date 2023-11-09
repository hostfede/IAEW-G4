package iaew.tpi.apiClientes.reservas.clients;

import org.springframework.stereotype.Service;

import java.util.List;

//https://www.baeldung.com/spring-5-webclient

/**
 * NO DEBERÍA HACER FALTA, CON EL GENERICO DEBERÍA SER SUFICIENTE
 * */
@Service
public interface IClienteReservas<T> {

    //Puede que en vez de hacer genérica la interfaz funcione mejor hacer genérico el método

    List<T> listarReservas(int idCliente);
    T getReserva(int idCliente, int idReserva);


}
