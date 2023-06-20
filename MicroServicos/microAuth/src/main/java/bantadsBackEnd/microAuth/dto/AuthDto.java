package bantadsBackEnd.microAuth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDto {

    private String email;
    private String nome;
    private String role;
    private String senha;
    private String cpf;

}
