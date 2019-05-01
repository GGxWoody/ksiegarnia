package pl.univ.ksiegarnia.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import pl.univ.ksiegarnia.model.Adres;

import java.util.List;


public interface AdresRepository extends PagingAndSortingRepository<Adres,Long> {
    List<Adres> findByMiasto(String Miasto);
    List<Adres> findByMiastoAndUlica(String Miasto,String ulica);
}
