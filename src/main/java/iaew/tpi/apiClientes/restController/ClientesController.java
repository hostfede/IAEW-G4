package iaew.tpi.apiClientes.restController;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class ClientesController {

    @GetMapping()
    public String holaMundo(){
        return "Hola Mundo!";
    }

}
