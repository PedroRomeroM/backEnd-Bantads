package bantadsbackend.microGerentes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GerenteDto {

    private Integer gerenteId;
    private String nomeGerente;
    private String emailGerente;
    private String cpfGerente;
    private String telefoneGerente;
    private String senhaGerente;


}
