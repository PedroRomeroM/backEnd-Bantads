package bantadsbackend.microGerentes.repository;

import bantadsbackend.microGerentes.model.Gerente;
import bantadsbackend.microGerentes.model.GerenteEditar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface GerenteRepository extends JpaRepository<Gerente, Integer> {


    @Query(value = "SELECT COUNT(*) AS quantidade_gerentes FROM gerentes", nativeQuery = true)
    Integer verificarGerentes();

    @Query(value = "SELECT gerente_id FROM gerentes WHERE cpf_gerente = :cpf", nativeQuery = true)
    Integer getGerenteId(@Param("cpf") String cpf);


//    @Transactional
//    @Modifying
//    @Query(value = "UPDATE gerentes SET nome_gerente = :novoNome, email_gerente = :novoEmail, telefone_gerente = :novoTelefone WHERE gerente_id = :gerenteId",nativeQuery = true)
//    int updateGerente(@Param("novoNome") String novoNome, @Param("novoEmail") String novoEmail, @Param("novoTelefone") String novoTelefone, @Param("gerenteId") Long gerenteId);

}
