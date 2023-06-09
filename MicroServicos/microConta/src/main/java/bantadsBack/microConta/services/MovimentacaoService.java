package bantadsBack.microConta.services;

import bantadsBack.microConta.dtos.ContaDTO;
import bantadsBack.microConta.dtos.MovimentacoesDTO;
import bantadsBack.microConta.modelCUD.ContaCUD;
import bantadsBack.microConta.modelCUD.MovimentacoesCUD;
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

//    @Autowired
//    private ContaRepositoryCUD repositoryCUD;
//    @Autowired
//    private MovimentacaoRepositoryCUD movimentacaoRepositoryCUD;
//    @Autowired
//    private MovimentacaoRepositoryR movimentacaoRepositoryR;
//
//    @Autowired
//    private ModelMapper mapper;
//
//
//    public MovimentacoesDTO despositar(String destinoMovimentacao, Float valorMovimentacao){
//        MovimentacoesCUD op = new MovimentacoesCUD();
//
//        Optional<ContaCUD> conta = repositoryCUD.findByIdCliente(destinoMovimentacao);
//
//        op.setDestinoMovimentacao(String.valueOf(conta.get()));
//        op.setOrigemMovimentacao(null);
//        op.setValorMovimentacao(valorMovimentacao);
//        op.setTipoMovimentacao(1);
//        op.setDataMovimentacao(null);
//
//        op = movimentacaoRepositoryCUD.save(op);
//
//        MovimentacoesDTO DTO = mapper.map(op, MovimentacoesDTO.class);
//
//        BigDecimal saldo = conta.get().getSaldoConta().add(BigDecimal.valueOf(valorMovimentacao));
//
//        updateSaldo(destinoMovimentacao, saldo);
//
//        return DTO;
//    }
//
//    public MovimentacoesDTO sacar(String destinoMovimentacao, Float valorMovimentacao) {
//
//        MovimentacoesCUD op = new MovimentacoesCUD();
//        Optional<ContaCUD> conta = repositoryCUD.findByIdCliente(destinoMovimentacao);
//
//        BigDecimal valorPossivel = conta.get().getSaldoConta().add(BigDecimal.valueOf(conta.get().getLimiteConta()));
//
//        if (valorPossivel.compareTo(BigDecimal.valueOf(valorMovimentacao)) < 0);
//
//        op.setDestinoMovimentacao(null);
//        op.setOrigemMovimentacao(String.valueOf(conta.get()));
//        op.setValorMovimentacao(valorMovimentacao);
//        op.setTipoMovimentacao(2);
//        op.setDataMovimentacao(null);
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
//
//
//    public MovimentacoesDTO transferir(String origemMovimentacao, String destinoMovimentacao, Float valorMovimentacao) {
//
//        MovimentacoesCUD op = new MovimentacoesCUD();
//
//        Optional<ContaCUD> deConta = repositoryCUD.findByIdCliente(origemMovimentacao);
//        Optional<ContaCUD> paraConta = repositoryCUD.findByIdCliente(destinoMovimentacao);
//
//        BigDecimal valorPossivel = deConta.get().getSaldoConta().add(BigDecimal.valueOf(deConta.get().getLimiteConta()));
//
//        if (valorPossivel.compareTo(BigDecimal.valueOf(valorMovimentacao)) < 0);
//
//        op.setIdMovimentacao(null);
//        op.setDestinoMovimentacao(String.valueOf(paraConta.get()));
//        op.setOrigemMovimentacao(String.valueOf(deConta.get()));
//        op.setValorMovimentacao(valorMovimentacao);
//        op.setTipoMovimentacao(3);
//        op.setDataMovimentacao(null);
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
//
//
//    public ContaCUD updateSaldo(String numConta, BigDecimal saldoConta){
//
//        Optional<ContaCUD> conta = repositoryCUD.findByIdCliente(numConta);
//        if (conta.isPresent());
//            ContaCUD ct = conta.get();
//            ct.setSaldoConta(saldoConta);
//            ct = repositoryCUD.save(ct);
//            ContaDTO dto = mapper.map(ct, ContaDTO.class);
//
//            return ct;
//    }


}
