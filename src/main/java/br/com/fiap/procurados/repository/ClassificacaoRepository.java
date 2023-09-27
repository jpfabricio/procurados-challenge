package br.com.fiap.procurados.repository;

import br.com.fiap.procurados.model.Caracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassificacaoRepository extends JpaRepository<Caracteristica, Integer> {
}
