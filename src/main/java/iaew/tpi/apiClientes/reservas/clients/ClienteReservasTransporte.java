package iaew.tpi.apiClientes.reservas.clients;

import iaew.tpi.apiClientes.reservas.transferObjects.ReservaTransporteDto;
import org.springframework.stereotype.Service;

import java.util.List;

//https://www.baeldung.com/spring-5-webclient
@Service("TRANSPORTE")
public class ClienteReservasTransporte implements IClienteReservas<ReservaTransporteDto> {

    @Override
    public List<ReservaTransporteDto> listarReservas(int idCliente) {
        return null;
    }

    @Override
    public ReservaTransporteDto getReserva(int idCliente, int idReserva) {
        return null;
    }
}
