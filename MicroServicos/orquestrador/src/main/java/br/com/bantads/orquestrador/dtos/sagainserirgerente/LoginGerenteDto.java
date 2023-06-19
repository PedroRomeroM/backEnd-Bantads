package br.com.bantads.orquestrador.dtos.sagainserirgerente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginGerenteDto {
    private String email;
    private String nome;
    private String role;
    private String senha;
}
