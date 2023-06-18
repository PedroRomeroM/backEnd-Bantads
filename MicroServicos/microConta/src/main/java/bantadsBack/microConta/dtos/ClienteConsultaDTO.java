package bantadsBack.microConta.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteConsultaDTO {

    private String nomeCliente;
    private String cpfCliente;
    private String nomeGerente;
    private float saldoConta;
}
