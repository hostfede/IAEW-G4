package iaew.tpi.apiClientes.restController;


import iaew.tpi.apiClientes.reservas.clients.IClienteReservasGen;
import iaew.tpi.apiClientes.reservas.transferObjects.ReservaAlojamientoDto;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ResponseWrapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private IClienteReservasGen clienteReservasGen;
    @Value("${baseUrl.reservasAlojamiento}")
    private String alojamientosBaseUrl;

    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable Integer id_cliente){
        return ResponseEntity.ok(new ClienteDto(id_cliente, "A", "A",
                "A", "A", LocalDate.now(),"1234"));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapperDto> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size){
        return ResponseEntity.ok(null);

    }

    /**
     * Endpoint de prueba para el cliente genérico. No deberían hacer falta los otros clientes.
     * */
    @GetMapping("/{id_cliente}/reservas-alojamiento")
    public ResponseEntity<List<ReservaAlojamientoDto>> getReservas(@PathVariable Integer id_cliente){

        List<ReservaAlojamientoDto> reservas = clienteReservasGen.getReservas(alojamientosBaseUrl, "reservas-alojamientos", id_cliente,ReservaAlojamientoDto.class);

        return ResponseEntity.ok(reservas);

    }

}
