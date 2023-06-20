package bantadsBack.microConta.dtos.sagaCadastrarCliente;

import bantadsBack.microConta.models.modelCUD.ContaCUD;
import bantadsBack.microConta.models.modelR.ContaR;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ContaDTO {

    private Long idConta;
    private float saldoConta;
    private float limiteConta;
    private Long idCliente; //todo
    private String situacaoConta;
    private Date dataCriacaoConta;
    private String nomeGerente;
    private Long idGerente;
}
