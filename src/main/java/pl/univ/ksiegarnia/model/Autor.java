package pl.univ.ksiegarnia.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imie;
    private String nazwisko;

    @ManyToMany(mappedBy = "autor")
    Set<Ksiazka> ksiazka = new HashSet<>();
}
