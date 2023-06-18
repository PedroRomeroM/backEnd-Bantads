package bantadsBack.microConta.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteTopDTO {

    private String nomeCliente;
    private String cpfCliente;
    private String cidadeCliente;
    private String estadoCliente;
    private String nomeGerente;
    private float saldoConta;

}
