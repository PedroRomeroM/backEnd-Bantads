package bantadsBack.microConta.models.modelCUD;

import bantadsBack.microConta.dtos.sagaCadastrarCliente.ContaDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="tb_conta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaCUD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conta")
    private Long idConta;

    @Column(nullable = false)
    private Long limiteConta;

    @Column(columnDefinition="CHAR(1)", nullable = false)
    private String situacaoConta;

    @Column(nullable = false)
    private BigDecimal saldoConta;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "data_conta")
    private Date dataCriacao;

    @Column(name = "id_cliente_conta", unique = true, nullable = false)
    private Long idCliente;

    @Column(name = "id_gerente_conta", nullable = false)
    private Long idGerente;


    public ContaCUD toQuery(){
        ModelMapper mapper = new ModelMapper();
        return mapper.map(this, ContaCUD.class);
    }

    public ContaDTO toDto() {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(this, ContaDTO.class);
    }

    public Long calculoLimite(Long salario){
        if(salario >= 2000.00){
            return salario/2;
        }
        return (long) 0.0;
    }
}
