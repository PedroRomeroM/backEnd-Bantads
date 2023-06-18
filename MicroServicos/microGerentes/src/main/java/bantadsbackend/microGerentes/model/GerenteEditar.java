package bantadsbackend.microGerentes.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GerenteEditar {

    @Id
    @Column(name = "nome_gerente")
    private String nomeGerente;

    @Column(name = "email_gerente")
    private String emailGerente;

    @Column(name = "cpf_gerente")
    private String cpfGerente;

    @Column(name = "telefone_gerente")
    private String telefoneGerente;

    @Column(name = "senha_gerente")
    private String senhaGerente;

}

