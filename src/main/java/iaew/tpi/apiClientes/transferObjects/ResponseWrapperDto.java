package iaew.tpi.apiClientes.transferObjects;

import java.util.List;

public record ResponseWrapperDto(int page, int size, long count, List<ClienteDto> results) {


}
