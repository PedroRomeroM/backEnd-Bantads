package bantadsBack.microConta.models.modelCUD;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DadosClienteCUD {

    @Id
    private Long clientId;

    @NotBlank
    @Size(max = 255)
    private String nomeCliente;

    @NotBlank
    @Size(min = 11,max = 11)
    private String cpfCliente;

    @NotNull
    private float salarioCliente;

    @NotBlank
    @Size(max = 50)
    private String cidadeCliente;

    @NotBlank
    @Size(min = 2,max = 2)
    private String estadoCliente;

    @Column(name="email_cliente")
    private String emailCliente;

}