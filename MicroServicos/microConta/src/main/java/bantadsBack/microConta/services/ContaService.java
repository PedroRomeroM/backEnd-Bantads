package bantadsBack.microConta.services;

import bantadsBack.microConta.dtos.ClienteContaDTO;
import bantadsBack.microConta.dtos.DadosContaDTO;
import bantadsBack.microConta.dtos.ContaDTO;
import bantadsBack.microConta.models.modelCUD.ContaCUD;
import bantadsBack.microConta.models.modelCUD.DadosClienteCUD;
import bantadsBack.microConta.models.modelR.ClienteR;
import bantadsBack.microConta.models.modelR.ContaR;
import bantadsBack.microConta.repositoryCUD.ContaRepositoryCUD;
import bantadsBack.microConta.repositoryCUD.DadosClienteRepository;
import bantadsBack.microConta.repositoryR.ClienteRepositoryR;
import bantadsBack.microConta.repositoryR.ContaRepositoryR;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.time.Instant;

import java.math.BigDecimal;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContaService {

    @Autowired
    private ContaRepositoryCUD repositoryCUD;

    @Autowired
    private ContaRepositoryR repositoryR;

    @Autowired
    private ClienteRepositoryR clienteRepositoryR;

    @Autowired
    private DadosClienteRepository dadosClienteRepository;

    @Autowired
    private ModelMapper mapper;

    public ContaDTO criarConta(Long clienteConta) {

            ContaCUD newConta = new ContaCUD();

            newConta.setSaldoConta(BigDecimal.ZERO);
            newConta.setSituacaoConta("E");
            newConta.setLimiteConta(BigDecimal.valueOf((float) 0));
            newConta.setIdCliente(clienteConta);
            newConta.setDataCriacao(null);

            newConta = repositoryCUD.save(newConta);

            return mapper.map(newConta, ContaDTO.class);
    }

    public DadosContaDTO salvarCliente(DadosContaDTO dadosContaDTO) {

        DadosClienteCUD cliente = mapper.map(dadosContaDTO, DadosClienteCUD.class);

        cliente = dadosClienteRepository.save(cliente);

        return mapper.map(cliente, DadosContaDTO.class);
    }


    public ContaDTO updateConta(Long idConta, @RequestBody ContaDTO dados) throws Exception {
        ContaR contaParaAtualizar = null;

        try {
            ContaCUD contaAtualizada = repositoryR.findById(idConta).get().toCommand();
        } catch (NoSuchElementException e) {
            throw new Exception(e);
        }

        contaParaAtualizar.setSaldoConta(dados.getSaldoConta());
        contaParaAtualizar.setLimiteConta(dados.getLimiteConta());
        contaParaAtualizar.setIdCliente(dados.getClientId());
        contaParaAtualizar = repositoryR.save(contaParaAtualizar);

        return mapper.map(contaParaAtualizar, ContaDTO.class);
    }

    public ContaDTO aprovarConta(Long idCliente) {
        Optional<ContaCUD> conta = repositoryCUD.findByIdCliente(idCliente);
        if(conta.isPresent()){
            ContaCUD ct = conta.get();
            Date dt = Date.from(Instant.now());
            ct.setSituacaoConta("A");
            ct.setDataCriacao(dt);

            ContaDTO dto2 = mapper.map(ct, ContaDTO.class);

            return dto2;
        } else{
            throw new RuntimeException();
        }
    }

    public ClienteContaDTO getById(Long id){
        Optional<ContaR> conta = repositoryR.findById(Long.valueOf(String.valueOf(id)));
        if(conta.isPresent());
            ContaDTO dto = mapper.map(conta.get(), ContaDTO.class);

            ClienteContaDTO dtoInfo = new ClienteContaDTO();

            dtoInfo.setClientId(dto.getClientId());
            dtoInfo.setIdConta(dto.getIdConta());
            dtoInfo.setSaldoCliente(dto.getSaldoConta());


            Optional<ClienteR> cliente = clienteRepositoryR.findById(String.valueOf(dto.getClientId()));


            if(cliente.isPresent()){
                DadosContaDTO dtoCliente = mapper.map(cliente, DadosContaDTO.class);
                dtoInfo.setCpfCliente(dtoCliente.getCpfCliente());
                dtoInfo.setNomeCliente(dtoCliente.getNomeCliente());
            }
            return dtoInfo;

    }

}
