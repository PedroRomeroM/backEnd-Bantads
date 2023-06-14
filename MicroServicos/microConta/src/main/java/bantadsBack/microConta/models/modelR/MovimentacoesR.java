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
@Table(name="tb_movimentacoes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacoesR {

    @Id
    private Long idMovimentacao;

    @CreationTimestamp
    @Column(name = "data_tempo")
    private Date dataTempoMovimentacao;

    private BigDecimal valorMovimentacao;

    @Column(columnDefinition="CHAR(1)")
    private String tipoMovimentacao;

    @ManyToOne
    @JoinColumn(name = "de_cliente")
    private ContaR deCliente;

    @ManyToOne
    @JoinColumn(name = "para_cliente")
    private ContaR paraCliente;

}
