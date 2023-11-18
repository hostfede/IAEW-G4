package iaew.tpi.apiClientes.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 60)
    private String nombre;
    @Basic
    @Column(name = "apellido", nullable = false, length = 60)
    private String apellido;
    @Basic
    @Column(name = "email", nullable = false, length = 60)
    private String email;
    @Basic
    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;
    @Basic
    @Column(name = "telefono", nullable = false, length = 60)
    private String telefono;
    @Basic
    @Column(name = "documento", nullable = false, length = 60)
    private String documento;
}
