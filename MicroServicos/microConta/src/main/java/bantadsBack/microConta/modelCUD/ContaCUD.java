package bantadsBack.microConta.modelCUD;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="tb_conta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaCUD {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_conta")
    private Long idConta;

    @Column(nullable = false)
    private BigDecimal limiteConta;

    @Column(columnDefinition="CHAR(1)", nullable = false)
    private String situacaoConta;

    @Column(nullable = false)
    private BigDecimal saldoConta;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dataCriacao;

    @Column(name = "id_cliente", unique = true, nullable = false)
    private Long idCliente;

    private Long idGerente;

    private String nomeGerente;

}
