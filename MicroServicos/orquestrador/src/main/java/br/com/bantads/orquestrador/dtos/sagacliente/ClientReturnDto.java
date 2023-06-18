package br.com.bantads.orquestrador.dtos.sagacliente;

import br.com.bantads.orquestrador.dtos.sagainserirgerente.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientReturnDto {
    private int clientId;
    private String nomeCliente;
    private String cpfCliente;
    private float salarioCliente;
    private Status status;
    private String emailCliente;
    private String cidadeCliente;
    private String estadoCliente;

}
