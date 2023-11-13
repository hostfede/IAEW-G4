package iaew.tpi.apiClientes.restController;


import iaew.tpi.apiClientes.controllers.DeleteController;
import iaew.tpi.apiClientes.controllers.GetController;
import iaew.tpi.apiClientes.controllers.PostController;
import iaew.tpi.apiClientes.controllers.PutController;
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
    @Autowired
    private GetController getController;
    @Autowired
    private PostController postController;
    @Autowired
    private PutController putController;
    @Autowired
    private DeleteController deleteController;
    @Value("${baseUrl.reservasAlojamiento}")
    private String alojamientosBaseUrl;

    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable Integer id_cliente){
        return ResponseEntity.ok(getController.getCliente(id_cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size){
        return ResponseEntity.ok(getController.getClientes(page, size));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(postController.createCliente(clienteDto));
    }

    @DeleteMapping
    public ResponseEntity<ClienteDto> deleteCliente(@PathVariable Integer id_cliente){
        return ResponseEntity.ok(deleteController.deleteCliente(id_cliente));
    }

    @PutMapping
    public ResponseEntity<ClienteDto> updateCliente(@RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(putController.updateCliente(clienteDto));
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
