package bantadsBack.microConta.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ConsultaGerenteDTO {

    private String nomeGerente;
    private String cpfGerente;
    private Integer numeroClientes;
    private float somaSaldosPositivos;
    private float somaSaldosNegativos;

}
