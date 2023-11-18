package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.persistence.entities.ClienteEntity;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    public ClienteDto createCliente(ClienteDto clienteDto) {
        ClienteEntity clienteEntity =  clienteMapper.mapToEntity(clienteDto);
        clienteRepository.save(clienteEntity);
        return clienteMapper.mapToDto(clienteEntity);
    }
}
