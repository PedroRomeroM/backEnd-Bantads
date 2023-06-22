package bantadsBack.microConta.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExtratoDto {
    private Long id;
    private Date data;
    private String tipo;
    private Long cliente;
    private Long origem;
    private Long destino;
    private float valor;
}
