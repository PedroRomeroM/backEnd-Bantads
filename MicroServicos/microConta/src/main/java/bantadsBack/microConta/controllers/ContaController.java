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

                if(dto.getAmmount() > 0) {
                    contaDTO.setSaldoConta((contaDTO.getSaldoConta() + dto.getAmmount()));
                }else {
                    if(contaDTO.getSaldoConta() > Math.abs(dto.getAmmount())){
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

    @GetMapping("/cliente/top")
    public ResponseEntity<List<ClienteTopDTO>> telaTopClientsManager(){

        List<ClienteTopDTO> i = contaService.consultarTopClientes();
        return ResponseEntity.status(HttpStatus.OK).body(i);
    }





}
