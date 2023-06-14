package bantadsBack.microConta.amqp;

import bantadsBack.microConta.dtos.DadosContaDTO;
import bantadsBack.microConta.dtos.ContaDTO;
import bantadsBack.microConta.dtos.ResponseDto;
import bantadsBack.microConta.services.ContaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContaListener {
    @Autowired
    private ContaService contaService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "criar-conta")
    public void receberMensagens(DadosContaDTO dto){

        //salvar cliente no banco
        DadosContaDTO cliente = contaService.salvarCliente(dto);
        //salvar conta no banco
        ContaDTO clienteDto = contaService.criarConta(dto.getIdCliente());
        //definir o dto da response
        ResponseDto rDto = new ResponseDto();
        //enviar mensagem para a fila do orquestrador
        rabbitTemplate.convertAndSend("fila-orquestrador",rDto);
    }
}