package bantadsBack.microConta.amqp;

import bantadsBack.microConta.dtos.sagaCadastrarCliente.DadosContaDTO;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.ContaDTO;
import bantadsBack.microConta.dtos.GerenteContaDTO;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.ResponseDto;
import bantadsBack.microConta.services.ContaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static bantadsBack.microConta.dtos.Status.SUCESSO;

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
        //Query gerente com menos clientes
        long idGerenteMenosClientes = contaService.consultarGerenteMenosContas();
        dto.setIdGerente(idGerenteMenosClientes);
        //Salvar conta no banco
        ContaDTO clienteDto = contaService.criarConta(dto);
        //Definir o dto da response
        ResponseDto rDto = new ResponseDto(SUCESSO);
        //Enviar mensagem para a fila do orquestrador
        rabbitTemplate.convertAndSend("fila-orquestrador-conta-criada",rDto);
        System.out.println("cliente criado com o id: "+dto.getClientId());
    }
}
