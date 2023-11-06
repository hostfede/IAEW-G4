package iaew.tpi.apiClientes.restController;


import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/clientes")
public class ClientesController {

    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable Integer id_cliente){
        return ResponseEntity.ok(new ClienteDto(id_cliente, "A", "A",
                "A", "A", LocalDate.now(),"1234"));
    }

    @GetMapping()
    public String holaMundo(){
        return "Hola Mundo!";
    }

}
