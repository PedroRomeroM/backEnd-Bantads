package bantadsBack.microConta.controllers;


import bantadsBack.microConta.dtos.*;
import bantadsBack.microConta.dtos.sagaCadastrarCliente.ContaDTO;
import bantadsBack.microConta.repositoryCUD.ContaRepositoryCUD;
import bantadsBack.microConta.repositoryR.ContaRepositoryR;
import bantadsBack.microConta.services.ContaService;
import bantadsBack.microConta.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/conta")
public class ContaController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private ContaRepositoryR contaRepositoryR;

    @Autowired
    private ContaRepositoryCUD contaRepositoryCUD;

    @GetMapping
    public ResponseEntity<List<ContaDTO>> listAll(){

        return ResponseEntity.status(HttpStatus.OK).body(contaService.selectAllContas());
    }
    @PutMapping(value = "/deposit/{cpf}")
    public ResponseEntity<Object> updateSaldo(@PathVariable("cpf") String cpf, @RequestBody MovimentacaoDto dto) {

            try{
                ContaDTO contaDTO = movimentacaoService.findClienteIdByCpf(cpf);

                if(dto.getAmmount() >= 0) {
                    contaDTO.setSaldoConta((contaDTO.getSaldoConta() + dto.getAmmount()));
                }else {
                    if(contaDTO.getSaldoConta() + (contaDTO.getLimiteConta()) >= Math.abs(dto.getAmmount())){
                        contaDTO.setSaldoConta(contaDTO.getSaldoConta() + dto.getAmmount());
                    }else {
                        return new ResponseEntity<>("Saldo Insuficiente", HttpStatus.BAD_REQUEST);
                    }
                }
                try {
                    ContaDTO contaAtualizada = contaService.updateConta(contaDTO);
                    return ResponseEntity.ok().body(contaAtualizada);
                }catch(Exception e){}

            } catch (NoSuchElementException e) {
                return new ResponseEntity<>("Cliente Não encontrado", HttpStatus.BAD_REQUEST);
            }
        return null;
    }

    @PutMapping(value = "/transfer/{cpfOrigem}/{cpfDestino}")
    public ResponseEntity<List<ContaDTO>> transferir(@PathVariable("cpfOrigem") String cpfOrigem, @PathVariable("cpfDestino") String cpfDestino ,@RequestBody MovimentacaoDto dto) {

        ContaDTO contaOrigem = movimentacaoService.findClienteIdByCpf(cpfOrigem);
        ContaDTO contaDestino = movimentacaoService.findClienteIdByCpf(cpfDestino);

        if(dto.getAmmount() > 0){
            contaOrigem.setSaldoConta(contaOrigem.getSaldoConta() - dto.getAmmount());
            contaDestino.setSaldoConta(contaDestino.getSaldoConta() + dto.getAmmount());

            ContaDTO contaOrigemAtualizada = contaService.updateConta(contaOrigem);
            ContaDTO contaDestinoAtualizada = contaService.updateConta(contaDestino);

            List<ContaDTO> list = new ArrayList<>();
            list.add(contaOrigemAtualizada);
            list.add(contaDestinoAtualizada);

            return ResponseEntity.ok().body(list);
        }

        return null;
    }


    @GetMapping("/admin")
    public ResponseEntity<List<ConsultaGerenteDTO>> telaInicialAdmin(){

        List<ConsultaGerenteDTO> i = contaService.consultarGerenteTelaInicialAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(i);
    }

    @GetMapping("/cliente")
    public ResponseEntity<List<ClienteConsultaDTO>> telaConsultaClienteAdmin(){

        List<ClienteConsultaDTO> i = contaService.consultarClientesAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(i);
    }

    @GetMapping("/cliente/esperando")
    public ResponseEntity<List<ClienteEsperaDTO>> telaInicialManager(){

        List<ClienteEsperaDTO> i = contaService.consultarClientesEsperando();
        return ResponseEntity.status(HttpStatus.OK).body(i);
    }

    @GetMapping("/cliente/inicial/{cpf}")
    public ResponseEntity<ClienteInicialDTO> getInitialScreenCliente(@PathVariable("cpf") String cpf){

        ClienteInicialDTO i = contaService.consultarClienteInicial(cpf);

        return ResponseEntity.status(HttpStatus.OK).body(i);
    }

    @GetMapping("/cliente/situacao/{cpf}")
    public ResponseEntity<ClienteSituacaoDTO> checkClientSituation(@PathVariable("cpf") String cpf){

        ClienteSituacaoDTO i = contaService.consultarSituacaoConta(cpf);
        return ResponseEntity.status(HttpStatus.OK).body(i);
    }

    @GetMapping("/cliente/top")
    public ResponseEntity<List<ClienteTopDTO>> telaTopClientsManager(){

        List<ClienteTopDTO> i = contaService.consultarTopClientes();
        return ResponseEntity.status(HttpStatus.OK).body(i);
    }





}
