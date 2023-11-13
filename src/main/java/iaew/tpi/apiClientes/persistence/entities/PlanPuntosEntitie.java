package iaew.tpi.apiClientes.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PlanPuntos")
public class PlanPuntosEntitie {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "cliente_id", nullable = false, insertable = false, updatable = false)
    private Integer clienteId;
    @Basic
    @Column(name = "puntos_acumulados", nullable = false)
    private Integer puntosAcumulados;
    @Basic
    @Column(name = "nivel", nullable = false, length = 60)
    private String nivel;
}
