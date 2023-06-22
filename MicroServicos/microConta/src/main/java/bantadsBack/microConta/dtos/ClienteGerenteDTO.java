package bantadsBack.microConta.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteGerenteDTO {

    private String nomeCliente;
    private String cpfCliente;
    private float salarioCliente;
    private String cidadeCliente;
    private String estadoCliente;

}
