package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.dtos.ContaAtribuidaAoNovoGerenteDto;
import bantadsBack.microConta.dtos.GerenteIDDto;
import bantadsBack.microConta.models.modelR.GerenteR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GerenteRepositoryR extends JpaRepository<GerenteR, Long> {

    @Query(nativeQuery = true,
            value = "SELECT subquery.id_gerente_conta " +
                    "FROM (" +
                    "   SELECT id_gerente_conta, COUNT(*) AS quantidade " +
                    "   FROM tb_conta " +
                    "   GROUP BY id_gerente_conta " +
                    "   ORDER BY quantidade DESC " +
                    "   LIMIT 1 " +
                    ") AS subquery"
    )
    public Long findAccountWithMostManagers();

    @Query(nativeQuery = true,
            value = "WITH random_account AS (" +
                    "SELECT id_conta " +
                    "FROM tb_conta " +
                    "WHERE id_gerente_conta = :gerente_antigo " +
                    "ORDER BY random() " +
                    "LIMIT 1" +
                    ") " +
                    "UPDATE tb_conta " +
                    "SET id_gerente_conta = :gerente_novo " +
                    "WHERE id_conta IN (SELECT id_conta FROM random_account) " +
                    "RETURNING id_conta")
    public Long substituirGerente(Long gerente_novo, Long gerente_antigo);

    @Query(value = "SELECT id_gerente_conta " +
            "FROM tb_conta " +
            "GROUP BY id_gerente_conta " +
            "ORDER BY COUNT(*) ASC, RANDOM() " +
            "LIMIT 1", nativeQuery = true)
    public Long gerenteComMenosClientes();


}
