package bantadsBack.microConta.models.modelR;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GerenteConsulta {
    @Id
    @Column(name = "nome_gerente")
    private String nomeGerente;

    @Column(name = "cpf_gerente")
    private String cpfGerente;

    @Column(name = "numero_clientes")
    private int numeroClientes;

    @Column(name = "soma_saldos_positivos")
    private float somaSaldosPositivos;

    @Column(name = "soma_saldos_negativos")
    private float somaSaldosNegativos;
}
