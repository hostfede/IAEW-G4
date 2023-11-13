package iaew.tpi.apiClientes.transferObjects;

import iaew.tpi.apiClientes.persistence.entities.ClienteEntitie;
import org.springframework.stereotype.Service;

@Service
public class ClienteMapper {

    public ClienteDto mapToDto(ClienteEntitie clienteEntitie){
        return new ClienteDto(
                clienteEntitie.getId(),
                clienteEntitie.getNombre(),
                clienteEntitie.getApellido(),
                clienteEntitie.getEmail(),
                clienteEntitie.getTelefono(),
                clienteEntitie.getFechaNacimiento(),
                clienteEntitie.getDocumento()
        );
    }

    public ClienteEntitie mapToEntitie(ClienteDto clienteDto){
        return new ClienteEntitie(
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
