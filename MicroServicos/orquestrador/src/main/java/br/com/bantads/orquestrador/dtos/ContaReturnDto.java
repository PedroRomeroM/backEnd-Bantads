package br.com.bantads.orquestrador.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContaReturnDto {
    private Status status;
    private String WhoAmI;
}
