package br.com.bantads.microClientes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
    private Integer clientId;
    private String nomeCliente;
    private String cpfCliente;
    private float salarioCliente;
    private int idadeCliente;
    private String emailCliente;
    private String cepCliente;
    private int numeroResidenciaCliente;
    private String complementoResidenciaCliente;
}
