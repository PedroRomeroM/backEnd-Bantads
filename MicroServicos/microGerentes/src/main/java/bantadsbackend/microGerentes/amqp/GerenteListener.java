package bantadsbackend.microGerentes.amqp;


import bantadsbackend.microGerentes.dto.GerenteDto;
import bantadsbackend.microGerentes.dto.ResponseDto;
import bantadsbackend.microGerentes.dto.Status;
import bantadsbackend.microGerentes.service.GerenteService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Component
public class GerenteListener {
    @Autowired
    private GerenteService gerenteService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "criar-gerente")
    public void receberMensagens(GerenteDto dto){
        //salvar gerente no banco
        long gerenteId = gerenteService.createGerente(dto);

        //definir o dto da response
        ResponseDto rDto = new ResponseDto(gerenteId, Status.SUCESSO);

        //enviar mensagem para a fila do orquestrador
        rabbitTemplate.convertAndSend("fila-orquestrador-gerente-criado",rDto);
    }
}
