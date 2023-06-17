package bantadsBack.microConta.models.modelR;

import bantadsBack.microConta.models.modelCUD.ContaCUD;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="tb_conta")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContaR {

    @Id
    @Column(name = "id_conta")
    private Long idConta;

    @Column(nullable = false)
    private BigDecimal saldoConta;

    @Column(nullable = false)
    private BigDecimal limiteConta;

    @Column(columnDefinition="CHAR(1)", nullable = false)
    private String situacaoConta;

    @Column(name = "id_cliente", unique = true, nullable = false)
    private Long idCliente;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date dataCriacaoConta;

    private Long idGerente;
    public ContaCUD toCommand(){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(this, ContaCUD.class);
    }



}
