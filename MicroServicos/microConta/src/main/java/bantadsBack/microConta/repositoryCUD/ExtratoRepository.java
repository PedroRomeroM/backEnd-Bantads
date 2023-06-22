package bantadsBack.microConta.repositoryCUD;

import bantadsBack.microConta.dtos.ExtratoDto;
import bantadsBack.microConta.models.modelCUD.DadosClienteCUD;
import bantadsBack.microConta.models.modelCUD.MovimentacoesCUD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ExtratoRepository extends JpaRepository<MovimentacoesCUD, ExtratoDto> {

    @Query(value = "SELECT * FROM tb_movimentacoes m " +
            "INNER JOIN tb_conta co ON m.origem = co.id_conta " +
            "INNER JOIN tb_conta cd ON m.destino = cd.id_conta " +
            "WHERE m.cliente = :clienteId " +
            "AND m.data >= :dataInicio " +
            "AND m.data <= :dataFim", nativeQuery = true)
    public List<ExtratoDto> getExtrato(@Param("clienteId") Long clienteId, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);



}
