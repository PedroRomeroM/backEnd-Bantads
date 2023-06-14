package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelCUD.ContaCUD;
import bantadsBack.microConta.models.modelR.ClienteR;
import bantadsBack.microConta.models.modelR.ContaR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepositoryR extends JpaRepository<ClienteR, String> {

}
