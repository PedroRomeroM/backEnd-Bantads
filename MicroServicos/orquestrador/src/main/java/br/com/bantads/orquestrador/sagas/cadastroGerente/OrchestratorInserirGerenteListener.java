package br.com.bantads.orquestrador.sagas.cadastroGerente;

import br.com.bantads.orquestrador.dtos.sagacliente.ClientReturnDto;
import br.com.bantads.orquestrador.dtos.sagacliente.ContaReturnDto;
import br.com.bantads.orquestrador.dtos.sagainserirgerente.GerenteResponseDto;
import br.com.bantads.orquestrador.dtos.sagainserirgerente.Status;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrchestratorInserirGerenteListener {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @RabbitListener(queues = "fila-orquestrador-gerente-criado")
    public void receberMensagensGerenteCriado(GerenteResponseDto dto){
        if(dto.getStatus() == Status.SUCESSO) {
            rabbitTemplate.convertAndSend("registrar-novo-gerente-no-micro-contas", dto);
        }else {
            //TODO remover gerente criado no micro de gerente;
        }
    }

    @RabbitListener(queues = "novo-gerente-registrado-no-micro-de-contas")
    public void receberMensagemDaContaCriada(GerenteResponseDto dto){
        if(dto.getStatus() == Status.SUCESSO) {
            rabbitTemplate.convertAndSend("registrar-novo-gerente-no-micro-contas", dto);
        }else {
            //TODO remover gerente criado no micro de gerente;
            //TODO remover gerente do micro de conta;
        }
    }
}
