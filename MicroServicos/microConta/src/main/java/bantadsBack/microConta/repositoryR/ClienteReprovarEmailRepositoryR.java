package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelR.ClienteGerenteR;
import bantadsBack.microConta.models.modelR.ClienteReprovarEmailR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteReprovarEmailRepositoryR extends JpaRepository<ClienteReprovarEmailR, Long> {

    @Query(value = "SELECT nome_cliente, email_cliente " +
            "FROM tb_cliente " +
            "WHERE cpf_cliente = :cpfCliente", nativeQuery = true)
    public ClienteReprovarEmailR getEmailNomeCliente(@Param("cpfCliente") String cpfCliente);


}
