package bantadsBack.microConta.dtos;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ClienteDTO {

    private Long idCliente;
    private String nomeCliente;
    private BigDecimal salarioCliente;
    private String cpfCliente;
    private String cidadeCliente;
    private String estadoCliente;



}
