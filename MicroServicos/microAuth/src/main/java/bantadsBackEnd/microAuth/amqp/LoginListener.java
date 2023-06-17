package bantadsBackEnd.microAuth.amqp;

import bantadsBackEnd.microAuth.controller.AuthController;
import bantadsBackEnd.microAuth.dto.AuthDto;
import bantadsBackEnd.microAuth.utils.EnviarEmail;
import bantadsBackEnd.microAuth.utils.GerarSenha;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;

@Component
public class LoginListener {

    @Autowired
    private AuthController authController;

    @Autowired
    private EnviarEmail enviarEmail;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "criar-login")
    public void receberMensagens(AuthDto dto) {
        //GERAR SENHA ALEATORIA DE 8 DIGITOS E SETAR NO DTO
        dto.setSenha(GerarSenha.generateRandomPassword(8));

        try {
            //SALVAR OS DADOS DE LOGIN NO BANCO
            String status = authController.register(dto);

            //PREPARAR O E-MAIL
            enviarEmail.setEmailDestinatario(dto.getEmail());
            enviarEmail.setNomeDestinatario(dto.getNome());
            enviarEmail.setSenhaDestinatario(dto.getSenha());



            //enviar mensagem para a fila do orquestrador
            rabbitTemplate.convertAndSend("fila-orquestrador-login-criado", status);
        }catch (Exception e){
            rabbitTemplate.convertAndSend("fila-orquestrador-login-criado", "FALHA AO CRIAR SENHA" + e);
        }
    }
}
