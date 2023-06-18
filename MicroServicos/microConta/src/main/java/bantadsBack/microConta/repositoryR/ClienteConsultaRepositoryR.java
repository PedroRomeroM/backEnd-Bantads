package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelR.ClienteConsultaR;
import bantadsBack.microConta.models.modelR.GerenteConsultaR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteConsultaRepositoryR extends JpaRepository<ClienteConsultaR, Long> {

    @Query(value = "SELECT c.nome_cliente, c.cpf_cliente, g.nome_gerente, ct.saldo_conta " +
            "FROM tb_cliente c " +
            "JOIN tb_conta ct ON c.client_id = ct.id_cliente_conta " +
            "JOIN tb_gerente g ON ct.id_gerente_conta = g.id_gerente", nativeQuery = true)
    public List<ClienteConsultaR> consultaCliente();

}
