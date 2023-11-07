package iaew.tpi.apiClientes.reservas.clients;

import iaew.tpi.apiClientes.reservas.transferObjects.ReservaExperienciaDto;
import org.springframework.stereotype.Service;

import java.util.List;

//https://www.baeldung.com/spring-5-webclient
@Service("EXPERIENCIAS")
public class ClienteReservasExpreciencias implements IClienteReservas<ReservaExperienciaDto> {
    @Override
    public List<ReservaExperienciaDto> listarReservas(int idCliente) {
        return null;
    }

    @Override
    public ReservaExperienciaDto getReserva(int idCliente, int idReserva) {
        return null;
    }
}
