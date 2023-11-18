package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.persistence.entities.ClienteEntity;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ClienteMapper;
import iaew.tpi.apiClientes.transferObjects.ResponseWrapperDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    public ClienteDto getCliente(Integer idCliente) {
        Optional<ClienteEntity> optionalClienteEntitie = clienteRepository.findById(idCliente);
        if(optionalClienteEntitie.isPresent())
            return clienteMapper.mapToDto(optionalClienteEntitie.get());
        else
            throw new APIException("No existe un cliente con el id provisto", null, HttpStatus.NOT_FOUND);
    }

    public ResponseWrapperDto getClientes(Integer numberPage, Integer size) {
        PageRequest pageRequest = PageRequest.of(numberPage-1, size);
        Page<ClienteEntity> page = clienteRepository.findAll(pageRequest);
        List<ClienteDto> clientes = page.getContent()
                .stream()
                .map(clienteEntitie -> clienteMapper.mapToDto(clienteEntitie))
                .toList();

        return new ResponseWrapperDto(numberPage, size, page.getTotalElements(), clientes);
    }
}
