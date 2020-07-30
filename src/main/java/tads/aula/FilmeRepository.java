package tads.aula;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
    List<Filme> findByDescricao(String descricao);
    List<Filme> findByAnoLancamento(Integer anoLancamento);


}
