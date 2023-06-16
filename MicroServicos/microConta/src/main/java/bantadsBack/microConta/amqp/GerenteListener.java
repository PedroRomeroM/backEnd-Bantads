package bantadsBack.microConta.amqp;

import bantadsBack.microConta.dtos.ContaDTO;
import bantadsBack.microConta.dtos.DadosContaDTO;
import bantadsBack.microConta.dtos.ResponseDto;
import bantadsBack.microConta.services.ContaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static bantadsBack.microConta.dtos.Status.SUCESSO;

@Component
public class GerenteListener {
    @Autowired
    private ContaService contaService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "consultar-gerente")
    public void receberMensagens(){

        ContaDTO clienteDto = contaService.criarConta(dto.getClientId());
        //definir o dto da response
        ResponseDto rDto = new ResponseDto(SUCESSO);
        //enviar mensagem para a fila do orquestrador
        rabbitTemplate.convertAndSend("fila-orquestrador-conta-criada",rDto);
        System.out.println("cliente criado com o id: "+dto.getClientId());
    }
}