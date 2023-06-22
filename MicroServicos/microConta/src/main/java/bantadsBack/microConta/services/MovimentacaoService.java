package bantadsBack.microConta.services;

import bantadsBack.microConta.dtos.DataFilterDto;
import bantadsBack.microConta.dtos.ExtratoDto;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.ContaDTO;
import bantadsBack.microConta.models.modelCUD.DadosClienteCUD;
import bantadsBack.microConta.models.modelCUD.MovimentacoesCUD;
import bantadsBack.microConta.repositoryCUD.ContaRepositoryCUD;
import bantadsBack.microConta.repositoryCUD.DadosClienteRepository;
import bantadsBack.microConta.repositoryCUD.ExtratoRepository;
import bantadsBack.microConta.repositoryCUD.MovimentacaoRepositoryCUD;
import bantadsBack.microConta.repositoryR.ContaRepositoryR;
import bantadsBack.microConta.repositoryR.MovimentacaoRepositoryR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimentacaoService {

    @Autowired
    private ContaRepositoryCUD contaRepositoryCUD;
    @Autowired
    private MovimentacaoRepositoryCUD movimentacaoRepositoryCUD;
    @Autowired
    private MovimentacaoRepositoryR movimentacaoRepositoryR;

    @Autowired
    ExtratoRepository extratoRepository;
    @Autowired
    ContaRepositoryR contaRepositoryR;

    @Autowired
    DadosClienteRepository dadosClienteRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    ContaService contaService;

    public ContaDTO findClienteIdByCpf(String cpf){
        DadosClienteCUD cliente = dadosClienteRepository.findClienteIdByCpf(cpf);
        ContaDTO conta = contaService.selectContaByIdCliente(cliente.getClientId());
        return mapper.map(conta,ContaDTO.class);
    }

    public void registrarDeposito(ExtratoDto dto){
        MovimentacoesCUD movimentacoesCUD = mapper.map(dto, MovimentacoesCUD.class);
        extratoRepository.save(movimentacoesCUD);
    }

    public void registrarSaque(ExtratoDto dto){
        extratoRepository.save(mapper.map(dto, MovimentacoesCUD.class));
    }

    public void registrarTransferencia(ExtratoDto dto){
        extratoRepository.save(mapper.map(dto, MovimentacoesCUD.class));
    }

    public List<ExtratoDto> selectAllExtratos(){
        return extratoRepository
                .findAll()
                .stream()
                .map(e -> mapper.map(e, ExtratoDto.class))
                .collect(Collectors.toList());
    }

    public List<ExtratoDto> selectExtrato(DataFilterDto dto){

        DadosClienteCUD cud = dadosClienteRepository.findClienteIdByCpf(dto.getCpf());
        List<MovimentacoesCUD> retorno = extratoRepository.getExtrato(cud.getClientId(),dto.getDataInicio(),dto.getDataFim());
        retorno.stream().map(e -> mapper.map(e, ExtratoDto.class)).collect(Collectors.toList());
        return retorno.stream().map(e -> mapper.map(e, ExtratoDto.class)).collect(Collectors.toList());
    }

}
