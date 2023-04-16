package br.com.bantads.microClientes.service;

import br.com.bantads.microClientes.dto.ClienteDto;
import br.com.bantads.microClientes.model.Cliente;
import br.com.bantads.microClientes.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ModelMapper modelMapperCliente;

    public Page<ClienteDto> selectAllClientes(Pageable page) {
        return clienteRepository
                .findAll(page)
                .map(e -> modelMapperCliente.map(e, ClienteDto.class));
    }

    public ClienteDto selectClienteById(Integer clientId) {
        Cliente cliente = clienteRepository.findById(clientId)
                .orElseThrow(() -> new EntityNotFoundException());

        return modelMapperCliente.map(cliente, ClienteDto.class);
    }

    public ClienteDto createCliente(ClienteDto dto) {
        Cliente cliente = modelMapperCliente.map(dto, Cliente.class);
        clienteRepository.save(cliente);

        return modelMapperCliente.map(cliente, ClienteDto.class);
    }
    public ClienteDto atualizarCliente(int clienteId, ClienteDto dto) {
        Cliente cliente = modelMapperCliente.map(dto, Cliente.class);
        cliente.setClientId(clienteId);
        cliente = clienteRepository.save(cliente);
        return modelMapperCliente.map(cliente, ClienteDto.class);
    }

    public void deleteCliente (int clienteId) {
        clienteRepository.deleteById(clienteId);
    }

}
