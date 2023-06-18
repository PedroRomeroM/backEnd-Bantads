package bantadsbackend.microGerentes.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GerenteEditarDTO {

    private String nomeGerente;
    private String emailGerente;
    private String cpfGerente;
    private String telefoneGerente;
    private String senhaGerente;
}
