package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelR.ClienteGerenteR;
import bantadsBack.microConta.models.modelR.ClienteTopR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteGerenteRepositoryR extends JpaRepository<ClienteGerenteR, Long> {

    @Query(value = "SELECT c.nome_cliente, c.cpf_cliente, c.salario_cliente, c.cidade_cliente, c.estado_cliente " +
            "FROM tb_cliente c " +
            "INNER JOIN tb_conta ct ON ct.id_cliente_conta = c.client_id " +
            "INNER JOIN tb_gerente g ON g.id_gerente = ct.id_gerente_conta " +
            "WHERE g.cpf_gerente = :cpfGerente", nativeQuery = true)
    public List<ClienteGerenteR> clientesDesseGerente(@Param("cpfGerente") String cpfGerente);




}
