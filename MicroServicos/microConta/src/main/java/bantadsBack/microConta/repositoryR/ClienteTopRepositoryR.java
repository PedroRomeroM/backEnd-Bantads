package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelR.ClienteTopR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteTopRepositoryR extends JpaRepository<ClienteTopR, Long> {


    @Query(value = "SELECT c.nome_cliente, c.cpf_cliente, c.cidade_cliente, c.estado_cliente, g.nome_gerente, co.saldo_conta " +
            "FROM tb_cliente c " +
            "INNER JOIN tb_conta co ON c.client_id = co.id_cliente_conta " +
            "INNER JOIN tb_gerente g ON co.id_gerente_conta = g.id_gerente " +
            "ORDER BY co.saldo_conta DESC " +
            "LIMIT 3", nativeQuery = true)
    public List<ClienteTopR> topClientes();
}
