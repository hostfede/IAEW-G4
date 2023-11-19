package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.persistence.entities.ClienteEntity;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ClienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Hashtable;

@Service
public class PostService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ClienteMapper clienteMapper;
    public ClienteDto createCliente(ClienteDto clienteDto) {

        if (clienteDto == null){
            throw new APIException("Los datos del cliente son obligatorios", null, HttpStatus.BAD_REQUEST);
        }
        clienteDto.validar();

        ClienteEntity clienteEntity =  clienteMapper.mapToEntity(clienteDto);
        try {
            clienteRepository.save(clienteEntity);
        } catch(DataIntegrityViolationException e){
            Hashtable<String, String> causas = new Hashtable<>();
            causas.put("NOT_UNIQUE", "Ya existe un cliente registrado con el email o número de documento proporcionado");
            throw new APIException("Ya existe un cliente registrado con el email o número de documento proporcionado", causas, HttpStatus.BAD_REQUEST);
        }
        return clienteMapper.mapToDto(clienteEntity);
    }
}
