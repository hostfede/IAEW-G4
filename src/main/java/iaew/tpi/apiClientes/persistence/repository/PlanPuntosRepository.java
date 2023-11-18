package iaew.tpi.apiClientes.persistence.repository;

import iaew.tpi.apiClientes.persistence.entities.PlanPuntosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanPuntosRepository extends JpaRepository<PlanPuntosEntity, Integer> {
}
