package pl.univ.ksiegarnia.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Ksiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String Tytul;

    @ManyToMany(mappedBy = "ksiazka")
    private Set<Zamowienia> zamowienia = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "Ksiazka_Autor",
            joinColumns = {@JoinColumn(name = "ID_Ksiazka")},
            inverseJoinColumns = {@JoinColumn(name = "ID_Autor")}
    )
    private Set<Autor> autor = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ID_Gatunek")
    private Gatunek gatunek;
}
