package iaew.tpi.apiClientes.transferObjects;

import iaew.tpi.apiClientes.persistence.entities.ClienteEntity;
import iaew.tpi.apiClientes.persistence.entities.PlanPuntosEntity;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    public ClienteDto mapToDto(ClienteEntity clienteEntity){
        return new ClienteDto(
                clienteEntity.getId(),
                clienteEntity.getNombre(),
                clienteEntity.getApellido(),
                clienteEntity.getEmail(),
                clienteEntity.getTelefono(),
                clienteEntity.getFechaNacimiento(),
                clienteEntity.getDocumento(),
                clienteEntity.getPlanPuntos() != null ? new PlanPuntosDto(clienteEntity.getPlanPuntos().getId(),
                        clienteEntity.getPlanPuntos().getPuntosAcumulados(),
                        clienteEntity.getPlanPuntos().getNivel()) : null
        );
    }

    public ClienteEntity mapToEntity(ClienteDto clienteDto){
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(clienteDto.getId());
        clienteEntity.setNombre(clienteDto.getNombre());
        clienteEntity.setApellido(clienteDto.getApellido());
        clienteEntity.setEmail(clienteDto.getEmail());
        clienteEntity.setFechaNacimiento(clienteDto.getFechaNacimiento());
        clienteEntity.setTelefono(clienteDto.getTelefono());
        clienteEntity.setDocumento(clienteDto.getDocumentoIdentidad());
        clienteEntity.setPlanPuntos(mapPlanPuntosToEntity(clienteDto.getPlanPuntos(), clienteEntity));

        return clienteEntity;

    }

    private PlanPuntosEntity mapPlanPuntosToEntity(PlanPuntosDto planPuntosDto, ClienteEntity clienteEntity){
        if(planPuntosDto == null){
            return null;
        }
        PlanPuntosEntity planPuntosEntity =  new PlanPuntosEntity();
        planPuntosEntity.setId(planPuntosDto.id());
        planPuntosEntity.setPuntosAcumulados(planPuntosDto.puntosAcumulados());
        planPuntosEntity.setNivel(planPuntosDto.nivel());
        planPuntosEntity.setCliente(clienteEntity);
        return planPuntosEntity;
    }
}
