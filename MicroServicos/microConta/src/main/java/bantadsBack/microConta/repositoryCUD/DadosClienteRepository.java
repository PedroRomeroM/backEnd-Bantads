package bantadsBack.microConta.repositoryCUD;

import bantadsBack.microConta.models.modelCUD.DadosClienteCUD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DadosClienteRepository extends JpaRepository<DadosClienteCUD, Long> {
}
