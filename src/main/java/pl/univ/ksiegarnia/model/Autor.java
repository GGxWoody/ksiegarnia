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
    private long ID;
    private String Imie;
    private String Nazwisko;

    @ManyToMany(mappedBy = "autor")
    Set<Ksiazka> ksiazka = new HashSet<>();
}
