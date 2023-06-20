package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelR.ClienteInicialR;
import bantadsBack.microConta.models.modelR.ClienteSituacaoR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClienteSituacaoRepositoryR extends JpaRepository<ClienteSituacaoR, Long> {

    @Query(value = "SELECT c.situacao_conta " +
            "FROM tb_cliente cl " +
            "INNER JOIN tb_conta c ON cl.client_id = c.id_cliente_conta " +
            "WHERE cl.cpf_cliente = :cpfCliente", nativeQuery = true)
    public ClienteSituacaoR getClientAccountSituation(@Param("cpfCliente") String cpfCliente);

}
