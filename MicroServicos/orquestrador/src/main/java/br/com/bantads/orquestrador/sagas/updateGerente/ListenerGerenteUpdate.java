package br.com.bantads.orquestrador.sagas.updateGerente;

import br.com.bantads.orquestrador.dtos.updateGerente.UpdateGerenteDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListenerGerenteUpdate {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = "gerente-atualizado-gerentes")
    public void receberMensagensUpdateGerente(UpdateGerenteDto dto) {
        //todo if aqui
        rabbitTemplate.convertAndSend("atualizar-gerente-contas", dto);
    }
}
