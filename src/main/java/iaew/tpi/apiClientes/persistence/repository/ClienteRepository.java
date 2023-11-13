package iaew.tpi.apiClientes.persistence.repository;

import iaew.tpi.apiClientes.persistence.entities.ClienteEntitie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntitie, Integer> {
}
