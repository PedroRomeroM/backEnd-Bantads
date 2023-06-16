package bantadsBack.microConta.models.modelR;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tb_gerente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GerenteIDR {

    @Id
    private Long idGerente;

    @Column(nullable = false)
    private String nomeGerente;

    @Column(length = 11, nullable = false, unique = true)
    private String cpfGerente;


}
