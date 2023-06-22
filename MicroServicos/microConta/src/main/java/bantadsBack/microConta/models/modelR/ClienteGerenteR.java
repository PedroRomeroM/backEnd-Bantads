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
public class ClienteGerenteR {

    @Id
    @Column(name = "nome_cliente")
    private String nomeCliente;

    @Column(name = "cpf_cliente")
    private String cpfCliente;

    @Column(name = "salario_cliente")
    private float salarioCliente;

    @Column(name = "cidade_cliente")
    private String cidadeCliente;

    @Column(name = "estado_cliente")
    private String estadoCliente;

}
