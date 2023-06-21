package br.com.bantads.orquestrador.sagas.updateCliente;

import br.com.bantads.orquestrador.dtos.sagacliente.ClienteDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListenerUpdategerente {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "update-cliente-conta")
    public void receberMensagemDaContaCriada(ClienteDto dto){
        rabbitTemplate.convertAndSend("atualizar-cliente-conta", dto);
    }
}
