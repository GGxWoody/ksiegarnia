package pl.univ.ksiegarnia.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.univ.ksiegarnia.model.Gatunek;

import java.util.List;

public interface GatunekRepository extends PagingAndSortingRepository<Gatunek,Long> {
    List<Gatunek> findByNazwa(String nazwa);
}
