package bantadsbackend.microGerentes.repository;

import bantadsbackend.microGerentes.model.Gerente;
import bantadsbackend.microGerentes.model.GerenteEditar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GerenteRepository extends JpaRepository<Gerente, Integer> {


    @Query(value = "SELECT COUNT(*) AS quantidade_gerentes FROM gerentes", nativeQuery = true)
    Integer verificarGerentes();

    @Query(value = "SELECT gerente_id FROM gerentes WHERE cpf_gerente = :cpf", nativeQuery = true)
    Integer getGerenteId(@Param("cpf") String cpf);



}
