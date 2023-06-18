package bantadsbackend.microGerentes.repository;

import bantadsbackend.microGerentes.model.Gerente;
import bantadsbackend.microGerentes.model.GerenteEditar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GerenteEditarRepository extends JpaRepository<GerenteEditar, String> {

    @Query(value = "select nome_gerente, email_gerente, cpf_gerente, telefone_gerente " +
            "from gerentes " +
            "where cpf_gerente = :cpf_gerente", nativeQuery = true)
    GerenteEditar findByCpf(String cpf_gerente);
}
