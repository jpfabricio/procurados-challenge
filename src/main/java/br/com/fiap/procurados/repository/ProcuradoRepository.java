package br.com.fiap.procurados.repository;

import br.com.fiap.procurados.model.Procurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProcuradoRepository extends JpaRepository<Procurado, Integer> {

    Procurado findByIdFbi(String idFbi);

    Procurado findByIdInterpol(String idInterpol);

    @Query("SELECT p FROM Procurado p WHERE p.idFbi = ?1 OR p.idInterpol = ?1")
    Procurado findByIdFbiOuIdInterpol(String idFbiOuIdInterpol);

    @Query("SELECT p FROM Procurado p WHERE (?1 IS NULL OR p.nome LIKE CONCAT('%', ?1, '%'))")
    List<Procurado> listaComFiltroOpcionalNome(String nome);

}
