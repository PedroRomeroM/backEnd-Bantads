package br.com.bantads.orquestrador.dtos.updateGerente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class UpdateGerenteDto {
    private String nomeGerente;
    private String emailGerente;
    private String cpfGerente;
    private String telefoneGerente;
    private String senhaGerente;
}
