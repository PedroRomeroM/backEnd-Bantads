package bantadsBack.microConta.models.modelCUD;


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
public class MovimentacoesCUD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @Column(name = "data")
    private Date data;

    @Column(name = "tipo")
    private String tipo;

    @Column(name="cliente")
    private Long cliente;

    @Column(name="origem")
    private Long origem;

    @Column(name="destino")
    private Long destino;

    @Column(name="valor")
    private BigDecimal valor;

}
