package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.persistence.entities.ClienteEntitie;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ClienteMapper;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;

    public ClienteDto deleteCliente(Integer idCliente) {
        Optional<ClienteEntitie> clienteToDelete = clienteRepository.findById(idCliente);
        if(clienteToDelete.isPresent())
            clienteRepository.delete(clienteToDelete.get());
        else
            throw new APIException();
        return clienteMapper.mapToDto(clienteToDelete.get());
    }
}
