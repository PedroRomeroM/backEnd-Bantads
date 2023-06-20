package bantadsBack.microConta.repositoryCUD;

import bantadsBack.microConta.models.modelCUD.DadosClienteCUD;
import bantadsBack.microConta.models.modelR.GerenteR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DadosClienteRepository extends JpaRepository<DadosClienteCUD, Long> {

    @Query(value = "SELECT * FROM tb_cliente WHERE cpf_cliente = :cpfCliente", nativeQuery = true)
    public DadosClienteCUD findClienteIdByCpf(String cpfCliente);


}
