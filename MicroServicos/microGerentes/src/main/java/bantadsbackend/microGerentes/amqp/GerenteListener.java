package bantadsbackend.microGerentes.amqp;


import bantadsbackend.microGerentes.dto.*;
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
        ResponseDto rDto = gerenteService.createGerente(dto);
        //enviar mensagem para a fila do orquestrador
        rabbitTemplate.convertAndSend("fila-orquestrador-gerente-criado",rDto);
    }

    @RabbitListener(queues = "excluir-gerente")
    public void receberMensagensExcluirGerente(EGerenteDto dto){
        //Deletar se houver mais de um gerente
        EResponseDto status = gerenteService.deleteGerente(dto.getCpf());

        //enviar mensagem para a fila do orquestrador
        rabbitTemplate.convertAndSend("gerente-excluido",status);
    }
}
