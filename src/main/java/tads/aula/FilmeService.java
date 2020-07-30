package tads.aula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

   private FilmeRepository filmeRepository;

    @Autowired
    public void setFilmeRepository(FilmeRepository filmeRepository){
        this.filmeRepository = filmeRepository;
    }

    public List<Filme> findAll(){
        return filmeRepository.findAll();
    }
    public void add(Filme filme){
        filmeRepository.save(filme);
    }
    public Filme get(Long id){
        return filmeRepository.getOne(id);
    }

    public void delete(Long id){
        filmeRepository.deleteById(id);
    }
}
