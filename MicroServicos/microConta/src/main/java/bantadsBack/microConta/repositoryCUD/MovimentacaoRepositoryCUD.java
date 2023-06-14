package bantadsBack.microConta.repositoryCUD;

import bantadsBack.microConta.models.modelCUD.MovimentacoesCUD;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimentacaoRepositoryCUD extends JpaRepository<MovimentacoesCUD, Integer> {
}
