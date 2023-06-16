package bantadsBack.microConta.repositoryR;

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
    public GerenteIDDto findAccountWithMostManagers();

}
