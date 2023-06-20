package bantadsBackEnd.microAuth.model;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("login")
@Getter
@Setter
public class Auth {


    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String role;

    @NotBlank
    @Size(max = 255)
    private String senha;

    @NotBlank
    @Size(max = 11)
    private String cpf;
}
