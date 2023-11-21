package iaew.tpi.apiClientes.transferObjects;

import iaew.tpi.apiClientes.reservas.transferObjects.AlojamientoDto;
import iaew.tpi.apiClientes.reservas.transferObjects.ExperienciaDto;
import iaew.tpi.apiClientes.reservas.transferObjects.TransporteDto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
public class ReservaWrapper {
    private int id;
    private LocalDate fecha_inicio;
    private LocalDate fecha_fin;
    private double precio_total;
    private String estado;
    private int participantes;
    private int huespedes;
    private TransporteDto transporte;
    private ExperienciaDto experiencia;
    private AlojamientoDto alojamiento;
    private String tipo_reserva;

    private ReservaWrapper(){}

    public static ReservaWrapper crearReservaTransporte(int id, TransporteDto transporte, LocalDate fecha_inicio,
                                                        LocalDate fecha_fin, double precio_total, String estado){

        ReservaWrapper reserva = new ReservaWrapper();
        reserva.transporte = transporte;
        reserva.id = id;
        reserva.fecha_inicio = fecha_inicio;
        reserva.fecha_fin = fecha_fin;
        reserva.precio_total = precio_total;
        reserva.estado = estado;
        reserva.tipo_reserva = "TRANSPORTE";
        return reserva;
    }

    public static ReservaWrapper crearReservaExperiencia(int id, ExperienciaDto experiencia, LocalDate fecha_inicio, int participantes,
                                                        double precio_total, String estado){

        ReservaWrapper reserva = new ReservaWrapper();
        reserva.experiencia = experiencia;
        reserva.id = id;
        reserva.fecha_inicio = fecha_inicio;
        reserva.participantes = participantes;
        reserva.precio_total = precio_total;
        reserva.estado = estado;
        reserva.tipo_reserva = "EXPERIENCIA";
        return reserva;
    }

    public static ReservaWrapper crearReservaAlojamiento(int id, AlojamientoDto alojamiento, LocalDate fecha_inicio,
                                                        LocalDate fecha_fin, double precio_total, String estado, int huespedes){

        ReservaWrapper reserva = new ReservaWrapper();
        reserva.alojamiento = alojamiento;
        reserva.id = id;
        reserva.fecha_inicio = fecha_inicio;
        reserva.fecha_fin = fecha_fin;
        reserva.precio_total = precio_total;
        reserva.huespedes = huespedes;
        reserva.estado = estado;
        reserva.tipo_reserva = "ALOJAMIENTO";
        return reserva;
    }
}
