package bantadsBackEnd.microAuth.amqp;

import bantadsBackEnd.microAuth.controller.AuthController;
import bantadsBackEnd.microAuth.dto.AuthDto;
import bantadsBackEnd.microAuth.dto.ResponseDto;
import bantadsBackEnd.microAuth.utils.EnviarEmail;
import bantadsBackEnd.microAuth.utils.GerarSenha;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import static bantadsBackEnd.microAuth.amqp.Status.ERRO;

@Component
public class LoginListener {

    @Autowired
    private AuthController authController;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "criar-login")
    public void receberMensagens(AuthDto dto) {
        //GERAR SENHA ALEATORIA DE 8 DIGITOS E SETAR NO DTO
        dto.setSenha(GerarSenha.generateRandomPassword(8));

        try {
            //SALVAR OS DADOS DE LOGIN NO BANCO
            Status status = authController.register(dto);
            //RESPONSE
            ResponseDto rdto = new ResponseDto(status);
            //enviar mensagem para a fila do orquestrador
            rabbitTemplate.convertAndSend("fila-orquestrador-login-criado", rdto);
        }catch (Exception e){
            ResponseDto rdto = new ResponseDto(ERRO);
            rabbitTemplate.convertAndSend("fila-orquestrador-login-criado", rdto);
        }
        //PREPARAR O E-MAIL
        EnviarEmail enviarEmail = new EnviarEmail();
        enviarEmail.setEmailDestinatario(dto.getEmail());
        enviarEmail.setNomeDestinatario(dto.getNome());
        enviarEmail.setSenhaDestinatario(dto.getSenha());
        enviarEmail.enviarGmail();
    }
}
