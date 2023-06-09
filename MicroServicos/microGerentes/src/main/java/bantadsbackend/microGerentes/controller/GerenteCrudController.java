package bantadsbackend.microGerentes.controller;

import bantadsbackend.microGerentes.dto.GerenteDto;
import bantadsbackend.microGerentes.service.GerenteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

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

    @PostMapping
    public ResponseEntity<GerenteDto> cadastroGerente(@RequestBody @Valid GerenteDto dto, UriComponentsBuilder uriBuilder){
        GerenteDto gerenteDto = gerenteService.createGerente(dto);
        URI path = uriBuilder.path("/gerentes/{id}").buildAndExpand(gerenteDto.getGerenteId()).toUri();

        return ResponseEntity.created(path).body(gerenteDto);

    }

    @PutMapping
    public ResponseEntity<GerenteDto> updateGerente(@PathVariable @NotNull int id, @RequestBody @Valid GerenteDto dto){
        GerenteDto updated = gerenteService.updateGerente(id, dto);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GerenteDto> deleteGerente(@PathVariable @NotNull int id){
        gerenteService.deleteGerente(id);

        return ResponseEntity.noContent().build();
    }

}