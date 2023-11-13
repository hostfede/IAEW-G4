package iaew.tpi.apiClientes.persistence.repository;

import iaew.tpi.apiClientes.persistence.entities.ClienteEntitie;
import iaew.tpi.apiClientes.persistence.entities.PlanPuntosEntitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanPuntosRepository extends JpaRepository<PlanPuntosEntitie, Integer> {
}
