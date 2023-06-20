package bantadsBack.microConta.repositoryCUD;

import bantadsBack.microConta.models.modelCUD.ContaCUD;
import bantadsBack.microConta.models.modelR.ContaR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ContaRepositoryCUD extends JpaRepository<ContaCUD, Long> {

    public List<ContaCUD> findAllBySituacaoConta(String sit);

    public Optional<ContaCUD> findByIdCliente(Long id);

    public List<ContaCUD> findByIdGerente(Long id);

}
