package bantadsBack.microConta.models.modelR;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteReprovarEmailR {

    @Id
    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "email_cliente")
    private String emailCliente;

}
