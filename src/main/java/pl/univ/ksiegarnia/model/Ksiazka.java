package pl.univ.ksiegarnia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Ksiazka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tytul;

    @ManyToMany(mappedBy = "ksiazka")
    @JsonIgnore
    private Set<Zamowienia> zamowienia = new HashSet<>();

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "Ksiazka_Autor",
            joinColumns = {@JoinColumn(name = "ID_Ksiazka")},
            inverseJoinColumns = {@JoinColumn(name = "ID_Autor")}
    )
    private Set<Autor> autor = new HashSet<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "ID_Gatunek")
    private Gatunek gatunek;
}
