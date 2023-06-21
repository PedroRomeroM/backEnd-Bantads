package bantadsBack.microConta.services;

import bantadsBack.microConta.dtos.*;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.ClienteContaDTO;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.ContaDTO;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.DadosContaDTO;
import bantadsBack.microConta.models.modelCUD.ContaCUD;
import bantadsBack.microConta.models.modelCUD.DadosClienteCUD;
import bantadsBack.microConta.models.modelCUD.DadosGerenteCUD;
import bantadsBack.microConta.models.modelR.ClienteInicialR;
import bantadsBack.microConta.models.modelR.ClienteR;
import bantadsBack.microConta.models.modelR.ClienteSituacaoR;
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
    private ContaRepositoryCUD contaRepositoryCUD;

    @Autowired
    private GerenteConsultaRepositoryR gerenteRepositoryConsulta;

    @Autowired
    private ClienteConsultaRepositoryR clienteConsultaRepositoryR;

    @Autowired
    private ClienteInicialRepositoryR clienteInicialRepositoryR;
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
    private ClienteSituacaoRepositoryR clienteSituacaoRepositoryR;

    @Autowired
    private ModelMapper mapper;

    public List<ContaDTO> selectAllContas() {
        return contaRepositoryCUD
                .findAll()
                .stream()
                .map(e -> mapper.map(e, ContaDTO.class))
                .collect(Collectors.toList());
    }

    public List<ClienteContaDTO> selectAllClientes() {
        return dadosClienteRepository
                .findAllClientes()
                .stream()
                .map(e -> mapper.map(e, ClienteContaDTO.class))
                .collect(Collectors.toList());
    }

    public List<GerenteContaDTO> selectAllGerentes() {
        return dadosGerenteRepository
                .findAll()
                .stream()
                .map(e -> mapper.map(e, GerenteContaDTO.class))
                .collect(Collectors.toList());
    }

    public ContaDTO selectContaByIdCliente(Long id) {
        return mapper.map(contaRepositoryCUD
                .findByIdCliente(id), ContaDTO.class);
    }

    public ContaDTO criarConta(DadosContaDTO clienteConta) {

        ContaCUD novaConta = new ContaCUD();

        novaConta.setSaldoConta(BigDecimal.ZERO);
        novaConta.setSituacaoConta("E");
        novaConta.setLimiteConta(novaConta.calculoLimite(clienteConta.getSalarioCliente()));
        novaConta.setIdCliente(clienteConta.getClientId());
        novaConta.setDataCriacao(null);
        novaConta.setIdGerente(clienteConta.getIdGerente());

        novaConta = contaRepositoryCUD.save(novaConta);

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

    public ClienteSituacaoDTO consultarSituacaoConta(String cpf){
        // consultar o gerente com mais contas e pegar o id

        ClienteSituacaoR situacaoConta = clienteSituacaoRepositoryR.getClientAccountSituation(cpf);

        return mapper.map(situacaoConta, ClienteSituacaoDTO.class);
    }

    public ClienteInicialDTO consultarClienteInicial(String cpf){
        // Tela Inicial cliente

        DadosClienteCUD cliente = dadosClienteRepository.findClienteIdByCpf(cpf);

        ClienteInicialR clienteInicial = clienteInicialRepositoryR.inicialCliente(cliente.getClientId());

        return mapper.map(clienteInicial, ClienteInicialDTO.class);
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

    public ContaDTO updateConta(ContaDTO dto){
        try {
            ContaCUD contaUpdate = mapper.map(dto, ContaCUD.class);

            contaUpdate = contaRepositoryCUD.save(contaUpdate);

            return mapper.map(contaUpdate, ContaDTO.class);
        }catch(Exception e){}
        return null;
    }

    public SalvarNovoGerenteDto updateGerente(SalvarNovoGerenteDto dto){
        try {
            Long id = contaRepositoryCUD.getGerenteIdByCpf(dto.getCpfGerente());
            DadosGerenteCUD dadosGerenteCUD = new DadosGerenteCUD(id,dto.getNomeGerente(), dto.getCpfGerente());

             DadosGerenteCUD cud = dadosGerenteRepository.save(dadosGerenteCUD);

            return dto;
        }catch(Exception e){}
        return null;
    }
    public ContaDTO getContaFromClientId(Long id){
       return mapper.map(contaRepositoryCUD.getContaByClientId(id),ContaDTO.class);
    }

//    public ContaDTO aprovarConta(Long idCliente) {
//        Optional<ContaCUD> conta = repositoryCUD.findByIdCliente(idCliente);
//        if(conta.isPresent()){
//            ContaCUD ct = conta.get();
//            Date dt = Date.from(Instant.now());
//            ct.setSituacaoConta("A");
//            ct.setDataCriacao(dt);
//
//            ContaDTO dto2 = mapper.map(ct, ContaDTO.class);
//
//            return dto2;
//        } else{
//            throw new RuntimeException();
//        }
//    }



}
