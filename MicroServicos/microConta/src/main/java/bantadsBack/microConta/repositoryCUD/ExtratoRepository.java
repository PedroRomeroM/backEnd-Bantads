package bantadsBack.microConta.repositoryCUD;

import bantadsBack.microConta.dtos.ExtratoDto;
import bantadsBack.microConta.models.modelCUD.DadosClienteCUD;
import bantadsBack.microConta.models.modelCUD.MovimentacoesCUD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExtratoRepository extends JpaRepository<MovimentacoesCUD, ExtratoDto> {

//    @Query(value = "SELECT * FROM tb_cliente", nativeQuery = true)
//    public List<DadosClienteCUD> findAllClientes();

}
