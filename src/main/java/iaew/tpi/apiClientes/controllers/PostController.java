package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.persistence.entities.ClienteEntitie;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    public ClienteDto createCliente(ClienteDto clienteDto) {
        ClienteEntitie clienteEntitie =  clienteMapper.mapToEntitie(clienteDto);
        clienteRepository.save(clienteEntitie);
        return clienteMapper.mapToDto(clienteEntitie);
    }
}
