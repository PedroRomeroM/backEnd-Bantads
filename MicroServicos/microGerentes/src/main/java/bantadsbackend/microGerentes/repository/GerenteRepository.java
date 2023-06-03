package bantadsbackend.microGerentes.repository;

import bantadsbackend.microGerentes.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GerenteRepository extends JpaRepository<Gerente, Integer> {
}
