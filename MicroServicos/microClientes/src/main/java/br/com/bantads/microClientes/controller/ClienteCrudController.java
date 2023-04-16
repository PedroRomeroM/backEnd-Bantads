package br.com.bantads.microClientes.controller;

import br.com.bantads.microClientes.dto.ClienteDto;
import br.com.bantads.microClientes.service.ClienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/crudclientes")
public class ClienteCrudController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Page<ClienteDto> listClientes (@PageableDefault(size = 10) Pageable paginacao) {
        return clienteService.selectAllClientes(paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> listClienteById(@PathVariable @NotNull int id) {
        ClienteDto dto = clienteService.selectClienteById(id);

        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ClienteDto> cadastroCliente(@RequestBody @Valid ClienteDto dto, UriComponentsBuilder uriBuilder) {
        ClienteDto clienteDto = clienteService.createCliente(dto);
        URI path = uriBuilder.path("/crudclientes/{id}").buildAndExpand(clienteDto.getClientId()).toUri();

        return ResponseEntity.created(path).body(clienteDto);
    }

    @PutMapping
    public ResponseEntity<ClienteDto> updateCliente(@PathVariable @NotNull int id, @RequestBody @Valid ClienteDto dto) {
        ClienteDto updated = clienteService.updateCliente(id,dto);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClienteDto> deleteCliente(@PathVariable @NotNull int id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
