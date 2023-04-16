package br.com.bantads.microClientes.repository;

import br.com.bantads.microClientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
