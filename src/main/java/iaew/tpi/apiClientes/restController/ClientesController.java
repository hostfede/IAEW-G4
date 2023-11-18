package iaew.tpi.apiClientes.restController;


import iaew.tpi.apiClientes.controllers.DeleteService;
import iaew.tpi.apiClientes.controllers.GetService;
import iaew.tpi.apiClientes.controllers.PostService;
import iaew.tpi.apiClientes.controllers.PutService;
import iaew.tpi.apiClientes.reservas.clients.IClienteReservasGen;
import iaew.tpi.apiClientes.reservas.transferObjects.ReservaAlojamientoDto;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ResponseWrapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @Autowired
    private IClienteReservasGen clienteReservasGen;
    @Autowired
    private GetService getService;
    @Autowired
    private PostService postService;
    @Autowired
    private PutService putService;
    @Autowired
    private DeleteService deleteService;
    @Value("${baseUrl.reservasAlojamiento}")
    private String alojamientosBaseUrl;

    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable Integer id_cliente){
        return ResponseEntity.ok(getService.getCliente(id_cliente));
    }

    @GetMapping
    public ResponseEntity<ResponseWrapperDto> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size){
        return ResponseEntity.ok(getService.getClientes(page, size));
    }

    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(postService.createCliente(clienteDto));
    }

    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> deleteCliente(@PathVariable("id_cliente") Integer id_cliente){
        return ResponseEntity.ok(deleteService.deleteCliente(id_cliente));
    }

    @PutMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable("id_cliente") Integer idCliente,@RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(putService.updateCliente(idCliente, clienteDto));
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
