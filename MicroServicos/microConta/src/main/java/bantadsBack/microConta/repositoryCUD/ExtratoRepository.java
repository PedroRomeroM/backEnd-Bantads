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

    @Query(value = "SELECT * FROM tb_movimentacoes " +
            "WHERE (cliente = :clienteId OR destino IN (SELECT id_conta FROM tb_conta WHERE id_cliente_conta = :clienteId)) " +
            "AND data >= :dataInicio " +
            "AND data <= :dataFim ",
            nativeQuery = true)
    public List<MovimentacoesCUD> getExtrato(@Param("clienteId") Long clienteId, @Param("dataInicio") Date dataInicio, @Param("dataFim") Date dataFim);

}
