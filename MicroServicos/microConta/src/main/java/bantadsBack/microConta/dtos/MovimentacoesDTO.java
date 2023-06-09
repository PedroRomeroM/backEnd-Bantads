package bantadsBack.microConta.dtos;

import bantadsBack.microConta.modelR.ContaR;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class MovimentacoesDTO {

    private Long idMovimentacao;
    private Date dataTempoMovimentacao;
    private BigDecimal valorMovimentacao;
    private String tipoMovimentacao;
    private ContaR deCliente;
    private ContaR paraCliente;

}
