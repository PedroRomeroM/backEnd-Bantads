package bantadsBack.microConta.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class ContaDTO {

    private Long idConta;
    private BigDecimal saldoConta;
    private BigDecimal limiteConta;
    private Long idCliente;
    private String situacaoConta;
    private Date dataCriacaoConta;
    private String nomeGerente;
    private Long idGerente;

}
