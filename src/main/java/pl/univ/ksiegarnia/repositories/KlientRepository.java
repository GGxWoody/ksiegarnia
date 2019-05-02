package pl.univ.ksiegarnia.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.univ.ksiegarnia.model.Klient;

import java.util.List;

public interface KlientRepository extends PagingAndSortingRepository<Klient,Long> {
    List<Klient> findByNazwiskoAndImie(String nazwisko,String imie);
}
