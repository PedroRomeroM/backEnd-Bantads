package br.com.bantads.orquestrador.sagas.autocadastro;

import br.com.bantads.orquestrador.dtos.sagacliente.ClientReturnDto;
import br.com.bantads.orquestrador.dtos.sagacliente.ContaReturnDto;
import br.com.bantads.orquestrador.dtos.sagacliente.LoginDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrchestratorAutoCadastroListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private String email;
    private String nome;
    private String role;
    private String senha;

    @RabbitListener(queues = "fila-orquestrador-cliente-criado")
    public void receberMensagensCliente(ClientReturnDto dto){
        rabbitTemplate.convertAndSend("criar-conta",dto);
        this.email = dto.getEmailCliente();
        this.nome = dto.getNomeCliente();
    }
    @RabbitListener(queues = "fila-orquestrador-conta-criada")
    public void receberMensagensConta(ContaReturnDto dto){
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(this.email);
        loginDto.setNome(this.nome);
        rabbitTemplate.convertAndSend("criar-login",loginDto);
    }
    @RabbitListener(queues = "fila-orquestrador-login-criado")
    public void receberMensagensLogin(ContaReturnDto dto){
        System.out.println("email enviado com suesso");
    }
}
