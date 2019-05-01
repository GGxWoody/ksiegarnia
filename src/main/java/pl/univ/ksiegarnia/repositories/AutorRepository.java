package pl.univ.ksiegarnia.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.univ.ksiegarnia.model.Autor;

import java.util.List;

public interface AutorRepository extends PagingAndSortingRepository<Autor,Long> {
    List<Autor> findByNazwiskoAndImie(String nazwisko,String imie);
}
