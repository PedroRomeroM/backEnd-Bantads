package bantadsBack.microConta.services;

import bantadsBack.microConta.dtos.ClienteContaDTO;
import bantadsBack.microConta.dtos.ClienteDTO;
import bantadsBack.microConta.dtos.ContaDTO;
import bantadsBack.microConta.modelCUD.ContaCUD;
import bantadsBack.microConta.modelR.ClienteR;
import bantadsBack.microConta.modelR.ContaR;
import bantadsBack.microConta.repositoryCUD.ContaRepositoryCUD;
import bantadsBack.microConta.repositoryR.ClienteRepositoryR;
import bantadsBack.microConta.repositoryR.ContaRepositoryR;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.time.Instant;

import java.math.BigDecimal;
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
    private ModelMapper mapper;

    public ContaDTO criarConta(Long clienteConta) {

            ContaCUD newConta = new ContaCUD();
            newConta.setSaldoConta(BigDecimal.ZERO);
            newConta.setSituacaoConta("E");
            newConta.setLimiteConta(BigDecimal.valueOf((float) 0));
            newConta.setIdCliente(clienteConta);
            newConta.setDataCriacao(null);

            newConta = repositoryCUD.save(newConta);

            ContaDTO dto = mapper.map(newConta, ContaDTO.class);

            return dto;
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
        Optional<ContaR> conta = repositoryR.findById(String.valueOf(id));
        if(conta.isPresent());
            ContaDTO dto = mapper.map(conta.get(), ContaDTO.class);

            ClienteContaDTO dtoInfo = new ClienteContaDTO();

            dtoInfo.setIdCliente(dto.getIdCliente());
            dtoInfo.setIdConta(dto.getIdConta());
            dtoInfo.setSaldoCliente(dto.getSaldoConta());


            Optional<ClienteR> cliente = clienteRepositoryR.findById(String.valueOf(dto.getIdCliente()));


            if(cliente.isPresent()){
                ClienteDTO dtoCliente = mapper.map(cliente, ClienteDTO.class);
                dtoInfo.setCpfCliente(dtoCliente.getCpfCliente());
                dtoInfo.setNomeCliente(dtoCliente.getNomeCliente());
                dtoInfo.setCidadeCliente(dtoCliente.getCidadeCliente());
                dtoInfo.setEstadoCliente(dtoCliente.getEstadoCliente());
            }
            return dtoInfo;

    }

}
