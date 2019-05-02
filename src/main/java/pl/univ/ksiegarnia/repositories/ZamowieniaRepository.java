package pl.univ.ksiegarnia.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.univ.ksiegarnia.model.Klient;

import pl.univ.ksiegarnia.model.Zamowienia;

import java.util.List;

public interface ZamowieniaRepository extends PagingAndSortingRepository<Zamowienia,Long> {
    List<Zamowienia> findByKlient(Klient klient);
}
