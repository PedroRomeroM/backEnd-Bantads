package br.com.bantads.microClientes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {
    private String clientId;
    private String nomeCliente;
    private String emailCliente;
    private String cpfCliente;
    private String telefoneCliente;
    private float salarioCliente;
    private String tipoEnderecoCliente;
    private String logradouroCliente;
    private String numeroResidenciaCliente;
    private String complementoResidenciaCliente;
    private String cepCliente;
    private String cidadeCliente;
    private String estadoCliente;
}
