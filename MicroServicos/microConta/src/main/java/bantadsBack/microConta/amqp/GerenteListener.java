package bantadsBack.microConta.amqp;

import bantadsBack.microConta.dtos.*;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.ResponseDto;
import bantadsBack.microConta.services.ContaService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static bantadsBack.microConta.dtos.Status.ERRO;
import static bantadsBack.microConta.dtos.Status.SUCESSO;

@Component
public class GerenteListener {
    @Autowired
    private ContaService contaService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "registrar-novo-gerente-no-micro-contas")
    public void receberMensagens(SalvarNovoGerenteDto dto){
        GerenteContaDTO dtoSalvarGerente = new GerenteContaDTO(dto.getGerenteId(),dto.getNomeGerente(),dto.getCpfGerente());
        //salvar gerente no banco
        GerenteContaDTO gerente = contaService.salvarGerente(dtoSalvarGerente);

        //Associar conta ao novo gerente

        try {
            //1º - Consultar qual gerente tem mais contas
            Long idDoGerenteComMaisContas = contaService.consultarGerente();
            //2º - Pegar uma conta aleatória associada a esse gerente e substituir para o novo gerente
            Long rDto = contaService.transferirConta(idDoGerenteComMaisContas,dto.getGerenteId());
            ResponseDto response = new ResponseDto(SUCESSO);
            //3º - Retornar o id da conta e uma mensagem de sucesso
            rabbitTemplate.convertAndSend("novo-gerente-registrado-no-micro-de-contas",response);
        }catch (Exception e){
            ResponseDto response = new ResponseDto(ERRO);
            rabbitTemplate.convertAndSend("novo-gerente-registrado-no-micro-de-contas",response);
        }
    }
}