package bantadsBack.microConta.models.modelR;


import bantadsBack.microConta.dtos.sagaCadastrarCliente.ContaDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Entity
@Table(name="tb_cliente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteR {

    @Id
    private Long idCliente;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(length = 11, nullable = false, unique = true)
    private String cpfCliente;

    public ContaDTO toDto() {
        ModelMapper mapper = new ModelMapper();
        return mapper.map(this, ContaDTO.class);
    }

}
