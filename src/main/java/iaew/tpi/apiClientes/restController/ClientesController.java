package iaew.tpi.apiClientes.restController;


import iaew.tpi.apiClientes.controllers.*;
import iaew.tpi.apiClientes.exceptions.ApiError;
import iaew.tpi.apiClientes.reservas.clients.IClienteReservasGen;
import iaew.tpi.apiClientes.reservas.transferObjects.ReservaAlojamientoDto;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ReservaWrapper;
import iaew.tpi.apiClientes.transferObjects.ResponseWrapperDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Autowired
    private ReservasService reservasService;
    @Value("${baseUrl.reservasAlojamiento}")
    private String alojamientosBaseUrl;

    @Operation(summary = "Recuperar un cliente por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se encontró el cliente",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)) })})
    @GetMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> getCliente(@PathVariable Integer id_cliente){
        return ResponseEntity.ok(getService.getCliente(id_cliente));
    }

    @Operation(summary = "Recuperar lista de clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de clientes",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseWrapperDto.class)) })
            })
    @GetMapping
    public ResponseEntity<ResponseWrapperDto> getAll(
            @RequestParam(name = "page", required = false, defaultValue = "1") int page,
            @RequestParam(name = "size", required = false, defaultValue = "10") int size){
        return ResponseEntity.ok(getService.getClientes(page, size));
    }

    @Operation(summary = "Modificar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)) }),
            @ApiResponse(responseCode = "400", description = "Formato de solicitud incorrecto",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)) })})
    @PostMapping
    public ResponseEntity<ClienteDto> createCliente(@RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(postService.createCliente(clienteDto));
    }

    @Operation(summary = "Modificar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)) }),
            @ApiResponse(responseCode = "400", description = "Formato de solicitud incorrecto",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)) })})
    @DeleteMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> deleteCliente(@PathVariable("id_cliente") Integer id_cliente){
        return ResponseEntity.ok(deleteService.deleteCliente(id_cliente));
    }

    @Operation(summary = "Modificar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ClienteDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)) }),
            @ApiResponse(responseCode = "400", description = "Formato de solicitud incorrecto",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)) })})
    @PutMapping("/{id_cliente}")
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable("id_cliente") Integer idCliente,@RequestBody ClienteDto clienteDto){
        return ResponseEntity.ok(putService.updateCliente(idCliente, clienteDto));
    }


    @Operation(summary = "Recuperar un cliente por su id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reservas de cliente encontradas",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ReservaWrapper.class))) }),
            @ApiResponse(responseCode = "404", description = "Cliente o reservas no encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class)) })})
    @GetMapping("/{id_cliente}/reservas")
    public ResponseEntity<List<ReservaWrapper>> getReservas(@PathVariable Integer id_cliente){
        List<ReservaWrapper> reservas = reservasService.getReservasCliente(id_cliente);
        return ResponseEntity.ok(reservas);
    }

}
