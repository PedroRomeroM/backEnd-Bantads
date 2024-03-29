package br.com.bantads.orquestrador.dtos.sagacliente;

import br.com.bantads.orquestrador.dtos.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContaReturnDto {
    private Status status;
}
