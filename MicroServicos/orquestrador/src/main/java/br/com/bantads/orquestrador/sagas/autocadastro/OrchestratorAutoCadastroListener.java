package br.com.bantads.orquestrador.sagas.autocadastro;

import br.com.bantads.orquestrador.dtos.sagacliente.ClientReturnDto;
import br.com.bantads.orquestrador.dtos.sagacliente.ContaReturnDto;
import br.com.bantads.orquestrador.dtos.sagacliente.LoginDto;
import br.com.bantads.orquestrador.dtos.sagainserirgerente.Status;
import br.com.bantads.orquestrador.utils.EnviarEmail;
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
        this.email = dto.getEmailCliente();
        this.nome = dto.getNomeCliente();

        if(dto.getStatus().equals(Status.SUCESSO)) {
            rabbitTemplate.convertAndSend("criar-conta",dto);
        }else {
            EnviarEmail enviarEmail = new EnviarEmail();
            enviarEmail.setNomeDestinatario(dto.getNomeCliente());

            enviarEmail.enviarGmail();
        }
    }
    @RabbitListener(queues = "fila-orquestrador-conta-criada")
    public void receberMensagensConta(ContaReturnDto dto){
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail(this.email);
        loginDto.setNome(this.nome);

        if(dto.getStatus().equals(Status.SUCESSO)) {
            rabbitTemplate.convertAndSend("criar-login",dto);
        }else {
            EnviarEmail enviarEmail = new EnviarEmail();
            enviarEmail.setNomeDestinatario(loginDto.getNome());

            enviarEmail.enviarGmail();
        }
    }
    @RabbitListener(queues = "fila-orquestrador-login-criado")
    public void receberMensagensLogin(ContaReturnDto dto){
        System.out.println("email enviado com sucesso");
    }
}
