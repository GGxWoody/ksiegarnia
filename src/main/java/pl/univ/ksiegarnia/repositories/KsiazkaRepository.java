package pl.univ.ksiegarnia.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.univ.ksiegarnia.model.Autor;
import pl.univ.ksiegarnia.model.Ksiazka;

import java.util.List;

public interface KsiazkaRepository extends PagingAndSortingRepository<Ksiazka,Long> {
    List<Ksiazka> findByTytul(String tytul);
    List<Ksiazka> findByAutor(Autor autor);
}
