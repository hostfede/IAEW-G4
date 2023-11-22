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

import java.util.ArrayList;
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
            List<ReservaWrapper> reservasWrapper = new ArrayList<>();

            // Ubiera estado bueno implementar un patrón BUILDER para crear las instancias de ReservasWrapper y así
            //poder hacer un método genérico que reciba el path, tipo de reserva y un string o algo de eso que identifique
            //la estrategia del builder
            try {
                reservasAlojamiento = clienteReservas
                        .getReservas(alojamientosBaseUrl, "/reservas-alojamiento", idCliente, ReservaAlojamientoDto.class);
                for (ReservaAlojamientoDto alojamiento: reservasAlojamiento
                     ) {
                    reservasWrapper.add(ReservaWrapper.crearReservaAlojamiento(alojamiento.id(),
                            alojamiento.alojamiento(), alojamiento.fecha_entrada(),
                            alojamiento.fecha_salida(), alojamiento.precio_total(), alojamiento.estado(),
                            alojamiento.huespedes()));
                }
            }catch (ReservasException re){
                reservasAlojamiento = null;
            }
            try {
                reservasExperiencia = clienteReservas
                        .getReservas(experienciasBaseUrl, "/reservas-experiencia", idCliente, ReservaExperienciaDto.class);
                for (ReservaExperienciaDto experiencia: reservasExperiencia
                ) {
                    reservasWrapper.add(ReservaWrapper.crearReservaExperiencia(experiencia.id(),
                            experiencia.experiencia(), experiencia.fecha()
                            , experiencia.participantes(), experiencia.precio_total(), experiencia.estado()));
                }
            }catch (ReservasException re){
                reservasExperiencia = null;
            }
            try {
                reservasTransporte = clienteReservas
                        .getReservas(transportesBaseUrl, "/reservas-transportes", idCliente, ReservaTransporteDto.class);
                for (ReservaTransporteDto transporte: reservasTransporte
                ) {
                    reservasWrapper.add(ReservaWrapper.crearReservaTransporte(transporte.id(),
                            transporte.transporte(), transporte.fecha_inicio(), transporte.fecha_fin(),
                            transporte.precio_total(), transporte.estado()));
                }
            }catch (ReservasException re){
                reservasTransporte = null;
            }
            if (reservasWrapper.isEmpty()){
                throw new APIException("El cliente no posee ninguna reserva realizada a su nombre", null, HttpStatus.NOT_FOUND);
            }
            return reservasWrapper;
        } else
            throw new APIException("No existe un cliente con el id provisto", null, HttpStatus.NOT_FOUND);
    }

}
