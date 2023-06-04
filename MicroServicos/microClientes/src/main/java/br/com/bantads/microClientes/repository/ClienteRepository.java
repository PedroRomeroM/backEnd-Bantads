package br.com.bantads.microClientes.repository;

import br.com.bantads.microClientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    @Query("from Cliente c where c.cpfCliente = :cpf_cliente")
    Optional<Cliente> findByCpf(String cpf_cliente);

    @Query("from Cliente c order by c.salarioCliente desc")
    List<Cliente> findTopClientes();
}
