package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelR.GerenteConsultaR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GerenteConsultaRepositoryR extends JpaRepository<GerenteConsultaR, Long> {

    @Query(value = "SELECT g.nome_gerente, g.cpf_gerente, COUNT(c.client_id) AS numero_clientes, " +
            "SUM(CASE WHEN co.saldo_conta > 0 THEN co.saldo_conta ELSE 0 END) AS soma_saldos_positivos, " +
            "SUM(CASE WHEN co.saldo_conta < 0 THEN co.saldo_conta ELSE 0 END) AS soma_saldos_negativos " +
            "FROM tb_gerente g " +
            "LEFT JOIN tb_conta co ON co.id_gerente_conta = g.id_gerente " +
            "LEFT JOIN tb_cliente c ON c.client_id = co.id_cliente_conta " +
            "GROUP BY g.nome_gerente, g.cpf_gerente", nativeQuery = true)
    public List<GerenteConsultaR> consultaGerente();

}
