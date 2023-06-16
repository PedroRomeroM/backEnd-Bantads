package bantadsBackEnd.microAuth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDto {

    private String email;
    private String nome;
    private String role;
    private String senha;

}
