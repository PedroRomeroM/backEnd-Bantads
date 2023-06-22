package br.com.bantads.orquestrador.dtos.sagainserirgerente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CriarLoginGerenteDto {
    private String email;
    private String nome;
    private String role;
    private String senha;
    private String cpf;
}
