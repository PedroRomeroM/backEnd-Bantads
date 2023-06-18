package br.com.bantads.orquestrador.sagas.excluirGerente;


import br.com.bantads.orquestrador.dtos.sagaExcluirGerente.ResponseGerenteDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrchestratorExcluirGerenteListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = "gerente-excluido")
    public void receberMensagensGerenteCriado(ResponseGerenteDto dto){
        if(dto.getStatus() == true) {
            rabbitTemplate.convertAndSend("excluir-gerente-conta", dto);
        }else {
            //TODO
        }
    }
}
