package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelR.ClienteEsperaR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteEsperaRepositoryR extends JpaRepository<ClienteEsperaR, Long> {

    @Query(value = "SELECT c.nome_cliente, c.cpf_cliente, c.salario_cliente " +
            "FROM tb_cliente c " +
            "INNER JOIN tb_conta co ON c.client_id = co.id_cliente_conta " +
            "WHERE co.situacao_conta = 'E'", nativeQuery = true)
    public List<ClienteEsperaR> todosClientesEsperando();
}
