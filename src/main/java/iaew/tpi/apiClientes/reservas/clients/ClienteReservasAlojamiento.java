package iaew.tpi.apiClientes.reservas.clients;


import iaew.tpi.apiClientes.reservas.transferObjects.ReservaAlojamientoDto;
import org.springframework.stereotype.Service;

import java.util.List;

//https://www.baeldung.com/spring-5-webclient
@Service("ALOJAMIENTO")
public class ClienteReservasAlojamiento implements IClienteReservas<ReservaAlojamientoDto> {

    @Override
    public List<ReservaAlojamientoDto> listarReservas(int idCliente) {
        return null;
    }

    @Override
    public ReservaAlojamientoDto getReserva(int idCliente, int idReserva) {
        return null;
    }
}
