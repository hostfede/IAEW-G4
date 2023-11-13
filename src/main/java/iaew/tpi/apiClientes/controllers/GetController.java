package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.persistence.entities.ClienteEntitie;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class GetController {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    public ClienteDto getCliente(Integer idCliente) {
        Optional<ClienteEntitie> optionalClienteEntitie = clienteRepository.findById(idCliente);
        if(optionalClienteEntitie.isPresent())
            return clienteMapper.mapToDto(optionalClienteEntitie.get());
        else
            throw new APIException();
    }

    public List<ClienteDto> getClientes(Integer numberPage, Integer size) {
        PageRequest pageRequest = PageRequest.of(numberPage-1, size);
        Page<ClienteEntitie> page = clienteRepository.findAll(pageRequest);
        return page.getContent()
                .stream()
                .map(clienteEntitie -> clienteMapper.mapToDto(clienteEntitie))
                .toList();
    }
}
