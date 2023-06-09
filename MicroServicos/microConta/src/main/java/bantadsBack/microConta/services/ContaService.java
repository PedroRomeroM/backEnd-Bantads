package bantadsBack.microConta.services;

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

//    @Autowired
//    private ContaRepositoryCUD repositoryCUD;
//
//    @Autowired
//    private ContaRepositoryR repositoryR;
//
//    @Autowired
//    private ClienteRepositoryR clienteRepositoryR;
//
//    @Autowired
//    private ModelMapper mapper;
//
//    public ContaDTO criarConta(String clienteConta) {
//
//            ContaCUD newConta = new ContaCUD();
//            newConta.setSaldoConta(BigDecimal.ZERO);
//            newConta.setSituacaoConta("E");
//            newConta.setLimiteConta((float) 0);
//            newConta.setClienteConta(clienteConta);
//            newConta.setNumConta(null);
//            newConta.setDataConta(null);
//
//            newConta = repositoryCUD.save(newConta);
//
//            ContaDTO dto = mapper.map(newConta, ContaDTO.class);
//
//            return dto;
//    }
//
//    public ContaDTO aprovarConta(String cpfCliente) {
//        Optional<ContaCUD> conta = repositoryCUD.findByIdCliente(cpfCliente);
//        if(conta.isPresent()){
//            ContaCUD ct = conta.get();
//            Date dt = Date.from(Instant.now());
//            ct.setSituacaoConta("A");
//            ct.setDataConta(dt);
//
//            ContaDTO dto2 = mapper.map(ct, ContaDTO.class);
//
//            return dto2;
//        } else{
//            throw new RuntimeException();
//        }
//    }
//
//    public ClienteDTO getById(Long id){
//        Optional<ContaR> conta = repositoryR.findById(String.valueOf(id));
//        if(conta.isPresent());
//            ContaDTO dto = mapper.map(conta.get(), ContaDTO.class);
//
//            ClienteDTO dtoInfo = new ClienteDTO();
//
////            dtoInfo.setIdCliente(dto.getIdCliente());
////            dtoInfo.setIdConta(dto.getIdConta());
////            dtoInfo.setSaldo(dto.getSaldo());
//
//
////            Optional<ClienteR> cliente = clienteRepositoryR.findById(dto.getIdCliente());
//
//
////            if(cliente.isPresent()){
////                ClienteContaDTO dtoCliente = mapper.map(cliente, ClienteContaDTO.class);
////                dtoInfo.setCpf(dtoCliente.getCpf());
////                dtoInfo.setNome(dtoCliente.getNome());
////                dtoInfo.setCidade(dtoCliente.getCidade());
////                dtoInfo.setEstado(dtoCliente.getEstado());
////            }
//            return dtoInfo;
//
//    }

}
