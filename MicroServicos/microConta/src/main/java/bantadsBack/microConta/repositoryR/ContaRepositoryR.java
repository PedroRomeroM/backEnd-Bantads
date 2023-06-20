package bantadsBack.microConta.repositoryR;

import bantadsBack.microConta.models.modelR.ContaR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ContaRepositoryR extends JpaRepository<ContaR, Long> {
    public ContaR findByIdConta(Long idConta);
}
