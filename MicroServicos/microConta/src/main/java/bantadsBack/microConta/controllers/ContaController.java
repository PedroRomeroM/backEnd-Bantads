package bantadsBack.microConta.controllers;


import bantadsBack.microConta.dtos.ContaDTO;
import bantadsBack.microConta.repositoryCUD.ContaRepositoryCUD;
import bantadsBack.microConta.repositoryR.ContaRepositoryR;
import bantadsBack.microConta.services.ContaService;
import bantadsBack.microConta.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
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

//    @PostMapping
//    public ResponseEntity<ContaDTO> criarConta(@NotNull @RequestBody ClienteDTO clienteDTO){
//        ContaDTO criar = contaService.criarConta(clienteDTO.getIdCliente());
//        return ResponseEntity.status(HttpStatus.OK).body(criar);
//    }

    @PutMapping(value = "/conta/{id}")
    public ResponseEntity<ContaDTO> updateConta(@PathVariable("id") Long id, @RequestBody ContaDTO account) throws Exception {
        try{
            ContaDTO contaAtualizada = contaService.updateConta(id, account);
            return ResponseEntity.ok().body(contaAtualizada);
        }catch (Exception e){
            throw new Exception(e);
        }



    }



}
