package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.persistence.entities.ClienteEntity;
import iaew.tpi.apiClientes.persistence.repository.ClienteRepository;
import iaew.tpi.apiClientes.transferObjects.ClienteDto;
import iaew.tpi.apiClientes.transferObjects.ClienteMapper;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
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
            try {
                clienteRepository.save(clienteEntity);
            }catch( DataIntegrityViolationException e){
                Map<String, String> causas = new HashMap<>();
                if(e.getMessage().contains("cliente.email")){
                    causas.put("EMAIL", "El email provisto ya pertenece a otro usuario registrado");
                }
                if(e.getMessage().contains("cliente.documento")){
                    causas.put("DOCUMENTO", "El documento provisto ya pertenece a otro usuario registrado");
                }
                throw new APIException("El número de documento o email provistos pertenecen a otro usuario registrado", causas, HttpStatus.BAD_REQUEST);
            }
        }else{
            throw new APIException("No existe un cliente con el id provisto", null, HttpStatus.NOT_FOUND);
        }
        return clienteMapper.mapToDto(clienteToUpdate.get());
    }
}
