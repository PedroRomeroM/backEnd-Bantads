package br.com.bantads.microClientes.dto;

import br.com.bantads.microClientes.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDto {
    private int clientId;
    private String nomeCliente;
    private String cpfCliente;
    private float salario;
    private Status status;
}
