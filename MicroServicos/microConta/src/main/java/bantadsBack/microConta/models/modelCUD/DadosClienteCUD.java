package bantadsBack.microConta.models.modelCUD;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

}