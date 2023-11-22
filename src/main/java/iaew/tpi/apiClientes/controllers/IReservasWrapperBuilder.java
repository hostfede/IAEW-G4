package iaew.tpi.apiClientes.controllers;

import iaew.tpi.apiClientes.transferObjects.ReservaWrapper;

public interface IReservasWrapperBuilder<T> {

    ReservaWrapper build(T reserva);

}
