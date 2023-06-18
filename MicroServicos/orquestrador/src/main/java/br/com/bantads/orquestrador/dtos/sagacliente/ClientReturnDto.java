package br.com.bantads.orquestrador.dtos.sagacliente;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientReturnDto {
    private Integer clientId;
    private String nomeCliente;
    private String cpfCliente;
    private float salarioCliente;
    private String emailCliente;
}
