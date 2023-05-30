package br.com.bantads.microClientes.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    @NotBlank
    @Size(max = 255)
    private String nomeCliente;

    @NotBlank
    @Size(max = 255)
    private String emailCliente;

    @NotBlank
    @Size(min = 11,max = 11)
    private String cpfCliente;

    @NotBlank
    @Size(min=11, max=20)
    private String telefoneCliente;

    @NotNull
    @Positive
    private float salarioCliente;

    @NotBlank
    @Size(max = 255)
    private String tipoEnderecoCliente;

    @NotBlank
    @Size(max = 255)
    private String logradouroCliente;

    @NotNull
    @Positive
    private int numeroResidenciaCliente;

    @NotBlank
    @Size(max = 255)
    private String complementoResidenciaCliente;

    @NotBlank
    @Size(min = 8, max = 8)
    private String cepCliente;

    @NotBlank
    @Size(max = 50)
    private String cidadeCliente;

    @NotBlank
    @Size(min = 2, max = 2)
    private String estadoCliente;

}
