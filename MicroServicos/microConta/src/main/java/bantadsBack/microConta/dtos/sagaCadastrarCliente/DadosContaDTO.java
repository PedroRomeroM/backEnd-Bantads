package bantadsBack.microConta.dtos.sagaCadastrarCliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DadosContaDTO {
    private Long clientId;
    private String nomeCliente;
    private String cpfCliente;
    private Long salarioCliente;
    private Long idGerente;
    private String cidadeCliente;
    private String estadoCliente;
}
