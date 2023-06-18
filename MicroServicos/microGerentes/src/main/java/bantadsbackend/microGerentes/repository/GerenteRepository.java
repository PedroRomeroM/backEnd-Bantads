package bantadsbackend.microGerentes.repository;

import bantadsbackend.microGerentes.model.Gerente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GerenteRepository extends JpaRepository<Gerente, Integer> {

//    @Query(value = "", nativeQuery = true)
//    public Gerente updateGerente(Gerente gerente);


}
