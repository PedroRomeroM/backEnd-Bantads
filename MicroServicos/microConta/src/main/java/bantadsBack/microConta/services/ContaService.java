package bantadsBack.microConta.services;

import bantadsBack.microConta.dtos.*;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.ClienteContaDTO;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.ContaDTO;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.DadosContaDTO;
import bantadsBack.microConta.models.modelCUD.ContaCUD;
import bantadsBack.microConta.models.modelCUD.DadosClienteCUD;
import bantadsBack.microConta.models.modelCUD.DadosGerenteCUD;
import bantadsBack.microConta.models.modelR.ClienteR;
import bantadsBack.microConta.models.modelR.ContaR;
import bantadsBack.microConta.repositoryCUD.ContaRepositoryCUD;
import bantadsBack.microConta.repositoryCUD.DadosClienteRepository;
import bantadsBack.microConta.repositoryCUD.DadosGerenteRepository;
import bantadsBack.microConta.repositoryR.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.time.Instant;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaService {

    @Autowired
    private ContaRepositoryCUD repositoryCUD;

    @Autowired
    private GerenteConsultaRepositoryR gerenteRepositoryConsulta;

    @Autowired
    private ClienteConsultaRepositoryR clienteConsultaRepositoryR;
    @Autowired
    private ContaRepositoryR repositoryR;

    @Autowired
    private ClienteRepositoryR clienteRepositoryR;

    @Autowired
    private DadosClienteRepository dadosClienteRepository;

    @Autowired
    private DadosGerenteRepository dadosGerenteRepository;

    @Autowired
    private GerenteRepositoryR gerenteRepositoryR;

    @Autowired
    private ClienteEsperaRepositoryR clienteEsperaRepositoryR;

    @Autowired
    private ClienteTopRepositoryR clienteTopRepositoryR;

    @Autowired
    private ModelMapper mapper;

    public ContaDTO criarConta(DadosContaDTO clienteConta) {

        ContaCUD novaConta = new ContaCUD();

        novaConta.setSaldoConta(BigDecimal.ZERO);
        novaConta.setSituacaoConta("E");
        novaConta.setLimiteConta(novaConta.calculoLimite(clienteConta.getSalarioCliente()));
        novaConta.setIdCliente(clienteConta.getClientId());
        novaConta.setDataCriacao(null);
        novaConta.setIdGerente(clienteConta.getIdGerente());

        novaConta = repositoryCUD.save(novaConta);

        return mapper.map(novaConta, ContaDTO.class);
    }

    public DadosContaDTO salvarCliente(DadosContaDTO dadosContaDTO) {

        DadosClienteCUD cliente = mapper.map(dadosContaDTO, DadosClienteCUD.class);

        cliente = dadosClienteRepository.save(cliente);

        return mapper.map(cliente, DadosContaDTO.class);
    }

    public GerenteContaDTO salvarGerente(GerenteContaDTO gerenteContaDTO){

        DadosGerenteCUD gerente = mapper.map(gerenteContaDTO, DadosGerenteCUD.class);

        gerente = dadosGerenteRepository.save(gerente);

        return mapper.map(gerente, GerenteContaDTO.class);
    }

    public Long consultarGerente(){
        // consultar o gerente com mais contas e pegar o id

        Long gerente = gerenteRepositoryR.findAccountWithMostManagers();


        return gerente;
    }

    public List<ClienteConsultaDTO> consultarClientesAdmin(){
        // Tela Inicial de Admin
        return clienteConsultaRepositoryR
                .consultaCliente()
                .stream()
                .map(e -> mapper.map(e, ClienteConsultaDTO.class))
                .collect(Collectors.toList());
    }

    public List<ClienteEsperaDTO> consultarClientesEsperando(){
        // Tela Inicial de Manager
        return clienteEsperaRepositoryR
                .todosClientesEsperando()
                .stream()
                .map(e -> mapper.map(e, ClienteEsperaDTO.class))
                .collect(Collectors.toList());
    }

    public List<ClienteTopDTO> consultarTopClientes(){
        // Tela Inicial de Manager
        return clienteTopRepositoryR
                .topClientes()
                .stream()
                .map(e -> mapper.map(e, ClienteTopDTO.class))
                .collect(Collectors.toList());
    }

    public List<ConsultaGerenteDTO> consultarGerenteTelaInicialAdmin(){
        // Tela Inicial de Admin
        return gerenteRepositoryConsulta
                .consultaGerente()
                .stream()
                .map(e -> mapper.map(e, ConsultaGerenteDTO.class))
                .collect(Collectors.toList());
    }

    public Long consultarGerenteMenosContas(){
        // consultar o gerente com menos contas e pegar o id
        Long gerente = gerenteRepositoryR.gerenteComMenosClientes();
        return gerente;
    }

    public Long consultarOSegundoGerenteMenosContas(){
        // consultar o gerente com menos contas e pegar o id
        Long gerente = gerenteRepositoryR.segundoGerenteComMenosClientes();
        return gerente;
    }

    public void transferirContaAoExcluirGerente(Long gerente_antigo,Long gerente_novo){
        gerenteRepositoryR.transferirClientesParaNovoGerente(gerente_antigo,gerente_novo);
        gerenteRepositoryR.deletarGerente(gerente_antigo);
    }

    public Long transferirConta(Long gerente_antigo,Long gerente_novo){
        return gerenteRepositoryR.substituirGerente(gerente_novo,gerente_antigo);
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
