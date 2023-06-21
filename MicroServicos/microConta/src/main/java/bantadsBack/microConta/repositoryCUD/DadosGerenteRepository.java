package bantadsBack.microConta.repositoryCUD;

import bantadsBack.microConta.models.modelCUD.DadosGerenteCUD;
import bantadsBack.microConta.models.modelR.GerenteR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DadosGerenteRepository extends JpaRepository<DadosGerenteCUD, Long> {

//    @Query(nativeQuery = true,
//            value = "select g.nome_gerente, g.cpf_gerente"+
//                    "from tb_conta c"+
//                    "join tb_gerente g ON c.id_gerente_conta = g.id_gerente"+
//                    "group by c.id_gerente_conta, g.nome_gerente, g.cpf_gerente"+
//                    "order by count(*) asc"+
//                    "limit 1;"
//    )
//    public GerenteR findWithLessClients();

}
