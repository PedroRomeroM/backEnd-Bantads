package bantadsBack.microConta.controllers;


import bantadsBack.microConta.services.ContaService;
import bantadsBack.microConta.services.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ContaController {

    @Autowired
    private MovimentacaoService movimentacaoService;


    @Autowired
    private ContaService contaService;



}
