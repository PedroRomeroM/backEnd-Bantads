package bantadsBack.microConta.modelR;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteR {

    @Id
    private Long idCliente;

    @Column(nullable = false)
    private String nomeCliente;

    @Column(nullable = false)
    private BigDecimal salarioCliente;

    @Column(length = 11, nullable = false, unique = true)
    private String cpfCliente;

    private String cidadeCliente;

    private String estadoCliente;

}
