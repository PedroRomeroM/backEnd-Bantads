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
        String senha = GerarSenha.generateRandomPassword(8);
        dto.setSenha(senha);

        try {
            //SALVAR OS DADOS DE LOGIN NO BANCO
            Status status = authController.register(dto);
            //RESPONSE
            ResponseDto rdto = new ResponseDto(status);
            //enviar mensagem para a fila do orquestrador
            rabbitTemplate.convertAndSend("fila-orquestrador-login-criado", rdto);

            if(status != ERRO) {
                //PREPARAR O E-MAIL
                EnviarEmail enviarEmail = new EnviarEmail();
                enviarEmail.setEmailDestinatario(dto.getEmail());
                enviarEmail.setNomeDestinatario(dto.getNome());
                enviarEmail.setSenhaDestinatario(senha);

                try {
                    enviarEmail.enviarGmail();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }

        }catch (Exception e){
            ResponseDto rdto = new ResponseDto(ERRO);
            rabbitTemplate.convertAndSend("fila-orquestrador-login-criado", rdto);
        }

    }

    @RabbitListener(queues = "registrar-novo-gerente-no-micro-login")
    public void receberMensagensLoginGerente(AuthDto dto) {
        try{
            authController.register(dto);
        }catch(Exception e){
            //todo
        }
    }
}
