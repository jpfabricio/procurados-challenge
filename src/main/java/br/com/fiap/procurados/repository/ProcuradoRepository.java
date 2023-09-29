package br.com.fiap.procurados.repository;

import br.com.fiap.procurados.model.Procurado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcuradoRepository extends JpaRepository<Procurado, Integer> {

    Procurado findByIdFbi(String idFbi);

    Procurado findByIdInterpol(String idInterpol);

}
