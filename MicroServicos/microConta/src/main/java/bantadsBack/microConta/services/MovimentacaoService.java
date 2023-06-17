package bantadsBack.microConta.services;

import bantadsBack.microConta.dtos.sagaCadastrarCliente.ContaDTO;
import bantadsBack.microConta.dtos.MovimentacoesDTO;
import bantadsBack.microConta.models.modelCUD.ContaCUD;
import bantadsBack.microConta.models.modelCUD.MovimentacoesCUD;
import bantadsBack.microConta.repositoryCUD.ContaRepositoryCUD;
import bantadsBack.microConta.repositoryCUD.MovimentacaoRepositoryCUD;
import bantadsBack.microConta.repositoryR.MovimentacaoRepositoryR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class MovimentacaoService {

    @Autowired
    private ContaRepositoryCUD repositoryCUD;
    @Autowired
    private MovimentacaoRepositoryCUD movimentacaoRepositoryCUD;
    @Autowired
    private MovimentacaoRepositoryR movimentacaoRepositoryR;

    @Autowired
    private ModelMapper mapper;


//    public MovimentacoesDTO despositar(Long destinoMovimentacao, BigDecimal valorMovimentacao){
//        MovimentacoesCUD op = new MovimentacoesCUD();
//
//        Optional<ContaCUD> conta = repositoryCUD.findByIdCliente(destinoMovimentacao);
//
//        op.setParaCliente(conta.get());
//        op.setDeCliente(null);
//        op.setValorMovimentacao(valorMovimentacao);
//        op.setTipoMovimentacao("D");
//        op.setDataTempoMovimentacao(null);
//
//        op = movimentacaoRepositoryCUD.save(op);
//
//        MovimentacoesDTO DTO = mapper.map(op, MovimentacoesDTO.class);
//
//        BigDecimal saldo = conta.get().getSaldoConta().add(valorMovimentacao);
//
//        updateSaldo(destinoMovimentacao, saldo);
//
//        return DTO;
//    }
//
//    public MovimentacoesDTO sacar(Long destinoMovimentacao, Float valorMovimentacao) {
//
//        MovimentacoesCUD op = new MovimentacoesCUD();
//        Optional<ContaCUD> conta = repositoryCUD.findByIdCliente(destinoMovimentacao);
//
//        //BigDecimal valorPossivel = conta.get().getSaldoConta().add(conta.get().getLimiteConta());
//
//        if (valorPossivel.compareTo(BigDecimal.valueOf(valorMovimentacao)) < 0);
//
//        op.setParaCliente(null);
//        op.setDeCliente(conta.get());
//        op.setValorMovimentacao(BigDecimal.valueOf(valorMovimentacao));
//        op.setTipoMovimentacao("S");
//        op.setDataTempoMovimentacao(null);
//
//        op = movimentacaoRepositoryCUD.save(op);
//
//        MovimentacoesDTO dto = mapper.map(op, MovimentacoesDTO.class);
//
//        BigDecimal saldo = conta.get().getSaldoConta().subtract(BigDecimal.valueOf(valorMovimentacao));
//
//        updateSaldo(destinoMovimentacao, saldo);
//
//        return dto;
//    }


//    public MovimentacoesDTO transferir(Long origemMovimentacao, Long destinoMovimentacao, Float valorMovimentacao) {
//
//        MovimentacoesCUD op = new MovimentacoesCUD();
//
//        Optional<ContaCUD> deConta = repositoryCUD.findByIdCliente(origemMovimentacao);
//        Optional<ContaCUD> paraConta = repositoryCUD.findByIdCliente(destinoMovimentacao);
//
//        BigDecimal valorPossivel = deConta.get().getSaldoConta().add(deConta.get().getLimiteConta());
//
//        if (valorPossivel.compareTo(BigDecimal.valueOf(valorMovimentacao)) < 0);
//
//        op.setIdMovimentacao(null);
//        op.setParaCliente(paraConta.get());
//        op.setDeCliente(deConta.get());
//        op.setValorMovimentacao(BigDecimal.valueOf(valorMovimentacao));
//        op.setTipoMovimentacao("T");
//        op.setDataTempoMovimentacao(null);
//
//        op = movimentacaoRepositoryCUD.save(op);
//        MovimentacoesDTO dto = mapper.map(op, MovimentacoesDTO.class);
//
//        BigDecimal saldoDe = deConta.get().getSaldoConta().subtract(BigDecimal.valueOf(valorMovimentacao));
//        updateSaldo(origemMovimentacao, saldoDe);
//
//
//        BigDecimal saldoPara = paraConta.get().getSaldoConta().add(BigDecimal.valueOf(valorMovimentacao));
//        updateSaldo(destinoMovimentacao, saldoPara);
//
//        return dto;
//    }


    public ContaCUD updateSaldo(Long idConta, BigDecimal saldoConta){

        Optional<ContaCUD> conta = repositoryCUD.findByIdCliente(idConta);
        if (conta.isPresent());
            ContaCUD ct = conta.get();
            ct.setSaldoConta(saldoConta);
            ct = repositoryCUD.save(ct);
            ContaDTO dto = mapper.map(ct, ContaDTO.class);

            return ct;
    }


}
