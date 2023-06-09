package bantadsBack.microConta.repositoryCUD;

import bantadsBack.microConta.modelCUD.ContaCUD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContaRepositoryCUD extends JpaRepository<ContaCUD, Long> {

    public List<ContaCUD> findAllBySituacaoConta(String sit);

    public Optional<ContaCUD> findByIdCliente(Long id);

    public List<ContaCUD> findByIdGerente(Long id);

}
