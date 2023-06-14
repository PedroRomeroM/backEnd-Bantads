package br.com.bantads.orquestrador.amqp;

import br.com.bantads.orquestrador.dtos.ClientReturnDto;
import br.com.bantads.orquestrador.dtos.ContaReturnDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrchestratorListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = "fila-orquestrador-cliente-criado")
    public void receberMensagensCliente(ClientReturnDto dto){
        if("MC".equals(dto.getWhoAmI())){
            rabbitTemplate.convertAndSend("criar-conta",dto);
            System.out.println("Pediu pra criar a conta");
        }
    }
    @RabbitListener(queues = "fila-orquestrador-conta-criada")
    public void receberMensagensConta(ContaReturnDto dto){
        System.out.println("debug");
        if("MA".equals(dto.getWhoAmI())){
            System.out.println(dto.getStatus() + " CRIOU PORRA");
        }
    }
}
