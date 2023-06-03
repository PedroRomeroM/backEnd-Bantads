package bantadsbackend.microGerentes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "gerentes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Gerente {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gerenteId;

    @NotBlank
    @Size(max = 255)
    private String nomeGerente;

    @NotBlank
    @Size(max = 255)
    private String emailGerente;

    @NotBlank
    @Size(min = 11, max = 11)
    private String cpfGerente;

    @NotBlank
    @Size(min = 11, max = 20)
    private String telefoneGerente;

    @NotBlank
    private String senhaGerente;

}
