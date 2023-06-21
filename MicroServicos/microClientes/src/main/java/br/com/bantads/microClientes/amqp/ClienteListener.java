package br.com.bantads.microClientes.amqp;

import br.com.bantads.microClientes.dto.ClienteDto;
import br.com.bantads.microClientes.dto.ResponseDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import br.com.bantads.microClientes.service.ClienteService;

import static br.com.bantads.microClientes.enums.Status.SUCESSO;

@Component
public class ClienteListener {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "criar-cliente")
    public void receberMensagens(ClienteDto dto){
        //salvar no banco
        int clientId = clienteService.createCliente(dto);
        //definir o dto da response
        ResponseDto rDto = new ResponseDto(clientId, dto.getNomeCliente(), dto.getCpfCliente(),dto.getSalarioCliente(),SUCESSO,dto.getEmailCliente(),dto.getCidadeCliente(),dto.getEstadoCliente());
        //enviar mensagem para a fila do orquestrador
        rabbitTemplate.convertAndSend("fila-orquestrador-cliente-criado",rDto);
        System.out.println("cliente criado com o id: "+clientId);
    }

    @RabbitListener(queues = "update-cliente")
    public void receberMensagensUpdate(ClienteDto dto){
        //setar o id
        ClienteDto selectCliente = clienteService.selectClientByCpf(dto.getCpfCliente());
        dto.setClientId(selectCliente.getClientId());
        clienteService.createCliente(dto);
        //enviar mensagem para a fila do orquestrador
        rabbitTemplate.convertAndSend("update-cliente-conta",dto);
    }
}
