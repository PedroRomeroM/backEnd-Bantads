package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelR.ClienteConsultaR;
import bantadsBack.microConta.models.modelR.ClienteInicialR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteInicialRepositoryR extends JpaRepository<ClienteInicialR, Long> {

    @Query(value = "SELECT c.nome_cliente, co.saldo_conta, co.limite_conta " +
            "FROM tb_cliente c " +
            "INNER JOIN tb_conta co ON c.client_id = co.id_cliente_conta " +
            "WHERE c.client_id = :idCliente", nativeQuery = true)
    public ClienteInicialR inicialCliente(@Param("idCliente") Long idCliente);


    @Query(value = "SELECT client_id " +
            "FROM tb_cliente " +
            "WHERE cpf_cliente = :cpfCliente", nativeQuery = true)
    public Long getClientId(@Param("cpfCliente") String cpfCliente);


}
