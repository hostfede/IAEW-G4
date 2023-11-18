package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.persistence.entities.ClienteEntity;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PutService {
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ClienteRepository clienteRepository;
    public ClienteDto updateCliente(Integer idCliente,ClienteDto clienteDto) {
        Optional<ClienteEntity> clienteToUpdate = clienteRepository.findById(idCliente);
        if(clienteToUpdate.isPresent()) {
            ClienteEntity clienteEntity = clienteMapper.mapToEntity(clienteDto);
            clienteEntity.setId(idCliente);
            clienteRepository.save(clienteEntity);
        }else{
            throw new APIException("No existe un cliente con el id provisto", null, HttpStatus.NOT_FOUND);
        }
        return clienteMapper.mapToDto(clienteToUpdate.get());
    }
}
