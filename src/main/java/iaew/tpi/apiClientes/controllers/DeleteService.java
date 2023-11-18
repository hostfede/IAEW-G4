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
public class DeleteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteDto deleteCliente(Integer idCliente) {
        Optional<ClienteEntity> clienteToDelete = clienteRepository.findById(idCliente);
        if(clienteToDelete.isPresent())
            clienteRepository.delete(clienteToDelete.get());
        else
            throw new APIException("No existe un cliente con el id provisto", null, HttpStatus.NOT_FOUND);
        return clienteMapper.mapToDto(clienteToDelete.get());
    }
}
