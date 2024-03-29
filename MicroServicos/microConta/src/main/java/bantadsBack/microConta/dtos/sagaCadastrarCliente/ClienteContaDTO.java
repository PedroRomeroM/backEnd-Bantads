package bantadsBack.microConta.dtos.sagaCadastrarCliente;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteContaDTO {

    private Long idConta;

    private Long clientId;

    private String nomeCliente;

    private String cpfCliente;

    private BigDecimal saldoCliente;

    private String cidadeCliente;

    private String estadoCliente;

}