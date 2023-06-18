package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.dtos.ConsultaGerenteDTO;
import bantadsBack.microConta.dtos.ContaAtribuidaAoNovoGerenteDto;
import bantadsBack.microConta.dtos.GerenteIDDto;
import bantadsBack.microConta.models.modelR.GerenteR;
import org.hibernate.mapping.Any;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    @Query(value = "SELECT id_gerente_conta " +
            "FROM tb_conta " +
            "GROUP BY id_gerente_conta " +
            "ORDER BY COUNT(*) ASC " +
            "OFFSET 1 LIMIT 1", nativeQuery = true)
    public Long segundoGerenteComMenosClientes();


    @Transactional
    @Modifying
    @Query(value = "UPDATE tb_conta SET id_gerente_conta = :novoGerenteId WHERE id_gerente_conta = :gerenteIdToDelete", nativeQuery = true)
    void transferirClientesParaNovoGerente(@Param("gerenteIdToDelete") Long gerenteIdToDelete, @Param("novoGerenteId") Long novoGerenteId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_gerente WHERE id_gerente = :gerenteIdToDelete", nativeQuery = true)
    void deletarGerente(@Param("gerenteIdToDelete") Long gerenteIdToDelete);


//    @Query(value = "SELECT g.nome_gerente, g.cpf_gerente, COUNT(c.client_id) AS numero_clientes, " +
//            "SUM(CASE WHEN co.saldo_conta > 0 THEN co.saldo_conta ELSE 0 END) AS soma_saldos_positivos, " +
//            "SUM(CASE WHEN co.saldo_conta < 0 THEN co.saldo_conta ELSE 0 END) AS soma_saldos_negativos " +
//            "FROM tb_gerente g " +
//            "LEFT JOIN tb_conta co ON co.id_gerente_conta = g.id_gerente " +
//            "LEFT JOIN tb_cliente c ON c.client_id = co.id_cliente_conta " +
//            "GROUP BY g.nome_gerente, g.cpf_gerente", nativeQuery = true)
//    public List<ConsultaGerenteDTO> consultaGerente();

}
