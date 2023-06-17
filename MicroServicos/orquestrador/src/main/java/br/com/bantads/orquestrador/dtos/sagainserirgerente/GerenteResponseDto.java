package br.com.bantads.orquestrador.dtos.sagainserirgerente;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class GerenteResponseDto {
    private long gerenteId;
    private String nomeGerente;
    private String cpfGerente;
    private Status status;
}
