package bantadsbackend.microGerentes.controller;

import bantadsbackend.microGerentes.dto.GerenteDto;
import bantadsbackend.microGerentes.dto.GerenteEditarDTO;
import bantadsbackend.microGerentes.service.GerenteService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gerentes")
@CrossOrigin(origins = "http://localhost:4200")
public class GerenteCrudController {

    @Autowired
    private GerenteService gerenteService;

    @GetMapping
    public ResponseEntity<List<GerenteDto>> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(gerenteService.selectAllGerente());
    }

//    @PostMapping
//    public ResponseEntity<GerenteDto> cadastroGerente(@RequestBody @Valid GerenteDto dto, UriComponentsBuilder uriBuilder){
//        GerenteDto gerenteDto = gerenteService.createGerente(dto);
//        URI path = uriBuilder.path("/gerentes/{id}").buildAndExpand(gerenteDto.getGerenteId()).toUri();
//
//        return ResponseEntity.created(path).body(gerenteDto);
//
//    }

    @PutMapping("/manager/{cpfGerente}")
    public ResponseEntity<Object> updateGerente(@PathVariable @NotNull String cpfGerente, @RequestBody GerenteDto dto){
        Integer updated = gerenteService.updateGerente(cpfGerente, dto);

        return ResponseEntity.ok(updated);
    }

    @GetMapping("/busca/{cpf_gerente}")
    public ResponseEntity<GerenteEditarDTO> gerenteParaEditar(@PathVariable @NotNull String cpf_gerente){

        return ResponseEntity.status(HttpStatus.OK).body(gerenteService.passarInfoEditarGerente(cpf_gerente));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<GerenteDto> deleteGerente(@PathVariable @NotNull int id){
//        gerenteService.deleteGerente(id);
//
//        return ResponseEntity.noContent().build();
//    }

}
