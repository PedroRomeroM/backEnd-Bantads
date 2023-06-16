package bantadsBack.microConta.models.modelCUD;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_gerente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosGerenteCUD {

    @Id
    private Long idGerente;

    @NotBlank
    @Size(max = 255)
    private String nomeGerente;

    @NotBlank
    @Size(min = 11,max = 11)
    private String cpfGerente;

}