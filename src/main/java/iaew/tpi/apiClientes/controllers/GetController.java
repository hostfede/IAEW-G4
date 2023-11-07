package iaew.tpi.apiClientes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import iaew.tpi.apiClientes.reservas.clients.IClienteReservas;

import java.util.Map;

@Service
public class GetController {

    @Autowired
    private Map<String, IClienteReservas> clientesReservas; //No estoy seguro de si esto funcionaría bien


}
