package bantadsBack.microConta.repositoryCUD;

import bantadsBack.microConta.models.modelCUD.DadosClienteCUD;
import bantadsBack.microConta.models.modelR.GerenteR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DadosClienteRepository extends JpaRepository<DadosClienteCUD, Long> {




}
