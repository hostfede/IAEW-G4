package iaew.tpi.apiClientes.reservas.clients;

import iaew.tpi.apiClientes.exceptions.APIException;
import iaew.tpi.apiClientes.exceptions.ReservasException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ClienteReservaGeneric implements IClienteReservasGen{

    /**
     * En caso de que nos retornen un único objeto que contenga tanto la lista como otros parámetros como page o size
     * */
    @Override
    public <T> T getReserva(String baseUrl, String endpoint, Integer cliente_id, Class<T> responseType) {

        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

        String path = "/clientes/"+ cliente_id + "/"+endpoint;
        return webClient.get()
                .uri(path)
                .retrieve()
                .bodyToMono(responseType)
                .block();

    }

    /**
     * En caso que devuelvan únicamente la lista de reseras (aunque no es recomendado)
     * */

    @Override
    public <T> List<T> getReservas(String baseUrl, String endpoint, Integer cliente_id, Class<T> responseType){
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        String path = String.format("/clientes/%d/%s", cliente_id, endpoint);

        return webClient.get()
                .uri(path)
                .retrieve()
                .onStatus(
                        HttpStatus.NOT_FOUND::equals,
                        response -> {throw new ReservasException("No hay reservas registradas a nombre del usuario especificado", HttpStatus.NOT_FOUND);}
                )
                .onStatus(
                        HttpStatus.INTERNAL_SERVER_ERROR::equals,
                        response -> {throw new ReservasException("Ocurrió un error al realizar la solicitud", HttpStatus.INTERNAL_SERVER_ERROR);}
                )
                .bodyToFlux(responseType)
                .collectList()
                .onErrorResume(WebClientResponseException.class, this::handleWebClientResponseException)
                .block();

    }

    private <T> Mono<T> handle4xxError(ClientResponse clientResponse) {
        if (clientResponse.statusCode() == HttpStatus.NOT_FOUND) {
            throw new APIException("El cliente indicado no posee reservas", null, HttpStatus.NOT_FOUND);
        } else if (clientResponse.statusCode() == HttpStatus.BAD_REQUEST) {
            throw new APIException("La solicitud no tiene un formati válido", null, HttpStatus.BAD_REQUEST);
        }
        // Puedes manejar otros códigos de error 4xx según tus necesidades
        throw new APIException("Ocurrió un error con la solicitud", null, HttpStatus.valueOf(clientResponse.statusCode().value()));
    }

    private <T> Mono<T> handle5xxError(ClientResponse clientResponse) {
        // Puedes manejar códigos de error 5xx según tus necesidades
        throw new APIException("Error 5xx no manejado", null, HttpStatus.valueOf(clientResponse.statusCode().value()));
    }
    private <T> Mono<T> handleWebClientResponseException(WebClientResponseException ex) {
        // Manejar excepciones WebClientResponseException
        return Mono.error(new APIException("Error de cliente en la llamada al servicio", null, HttpStatus.valueOf(ex.getStatusCode().value())));
    }
}
