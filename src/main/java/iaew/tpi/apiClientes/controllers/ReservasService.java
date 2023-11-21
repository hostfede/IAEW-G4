package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.exceptions.ReservasException;
import iaew.tpi.apiClientes.persistence.entities.ClienteEntity;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.reservas.clients.IClienteReservasGen;
import iaew.tpi.apiClientes.reservas.transferObjects.ReservaAlojamientoDto;
import iaew.tpi.apiClientes.reservas.transferObjects.ReservaExperienciaDto;
import iaew.tpi.apiClientes.reservas.transferObjects.ReservaTransporteDto;
import iaew.tpi.apiClientes.transferObjects.ReservaWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservasService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IClienteReservasGen clienteReservas;
    @Value("${baseUrl.reservasAlojamiento}")
    private String alojamientosBaseUrl;
    @Value("${baseUrl.reservasExperiencias}")
    private String experienciasBaseUrl;
    @Value("${baseUrl.reservasTransporte}")
    private String transportesBaseUrl;


    public List<ReservaWrapper> getReservasCliente(Integer idCliente){

        Optional<ClienteEntity> optionalClienteEntitie = clienteRepository.findById(idCliente);
        if(optionalClienteEntitie.isPresent()){
            List<ReservaExperienciaDto> reservasExperiencia;
            List<ReservaAlojamientoDto> reservasAlojamiento;
            List<ReservaTransporteDto> reservasTransporte;
            // Se podría hacer más genérico o incluso aplicar un patrón strategy pero no sé si vale la pena
            try {
                reservasExperiencia = clienteReservas
                        .getReservas(experienciasBaseUrl, "/reservas-experiencias", idCliente, ReservaExperienciaDto.class);
            }catch (ReservasException re){
                reservasExperiencia = null;
            }

        } else
            throw new APIException("No existe un cliente con el id provisto", null, HttpStatus.NOT_FOUND);

        return null;

    }

}
