package bantadsBack.microConta.controllers;


import bantadsBack.microConta.dtos.ConsultaGerenteDTO;
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


//    @PutMapping(value = "/conta/{id}")
//    public ResponseEntity<ContaDTO> updateConta(@PathVariable("id") Long id, @RequestBody ContaDTO account) throws Exception {
//        try{
//            ContaDTO contaAtualizada = contaService.updateConta(id, account);
//            return ResponseEntity.ok().body(contaAtualizada);
//        }catch (Exception e) {
//            throw new Exception(e);
//        }
//    }

    @GetMapping("/admin")
    public ResponseEntity<List<ConsultaGerenteDTO>> telaInicialAdmin(){

        List<ConsultaGerenteDTO> i = contaService.consultarGerenteTelaInicialAdmin();
        return ResponseEntity.status(HttpStatus.OK).body(i);
    }





}
