package iaew.tpi.apiClientes.transferObjects;

import iaew.tpi.apiClientes.persistence.entities.ClienteEntity;
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
                clienteEntity.getDocumento()
        );
    }

    public ClienteEntity mapToEntity(ClienteDto clienteDto){
        return new ClienteEntity(
                clienteDto.id(),
                clienteDto.nombre(),
                clienteDto.apellido(),
                clienteDto.email(),
                clienteDto.fecha_nacimiento(),
                clienteDto.telefono(),
                clienteDto.documento_identidad()
        );
    }
}
