package iaew.tpi.apiClientes.reservas.clients;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

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
    public <T> List<T> getReservas(String baseUrl, String endpoint, Integer cliente_id, Class<T> responseType) {
        WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();
        String path = String.format("/clientes/%d/%s", cliente_id, endpoint);
        Flux<T> responseFlux = webClient.get()
                .uri(path)
                .retrieve()
                .bodyToFlux(responseType);
        return responseFlux.collectList().block();

    }
}
