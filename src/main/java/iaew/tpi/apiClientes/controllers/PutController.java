package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.persistence.entities.ClienteEntitie;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PutController {
    @Autowired
    private ClienteMapper clienteMapper;
    @Autowired
    private ClienteRepository clienteRepository;
    public ClienteDto updateCliente(ClienteDto clienteDto) {
        Optional<ClienteEntitie> clienteToUpdate = clienteRepository.findById(clienteDto.id());
        if(clienteToUpdate.isPresent())
            clienteRepository.save(clienteMapper.mapToEntitie(clienteDto));
        else
            throw new APIException();
        return clienteMapper.mapToDto(clienteToUpdate.get());
    }
}
