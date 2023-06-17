package bantadsBackEnd.microAuth.amqp;

import bantadsBackEnd.microAuth.controller.AuthController;
import bantadsBackEnd.microAuth.dto.AuthDto;
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
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "criar-login")
    public void receberMensagens(AuthDto dto) {
//        try {
//            authController.register(dto);
//            //R
//
//            //enviar mensagem para a fila do orquestrador
//            rabbitTemplate.convertAndSend("fila-orquestrador-login-criado", rDto);
//        }catch (Exception e){
//            rabbitTemplate.convertAndSend("fila-orquestrador-login-criado", rDto);
//        }
    }
}
