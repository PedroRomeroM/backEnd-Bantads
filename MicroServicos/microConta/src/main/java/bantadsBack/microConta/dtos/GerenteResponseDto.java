package bantadsBack.microConta.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class GerenteResponseDto {
    private long gerenteId;
    private String nomeGerente;
    private String cpfGerente;
    private Status status;
    private String emailGerente;
    private String senha;
}
