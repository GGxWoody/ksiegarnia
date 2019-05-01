package pl.univ.ksiegarnia.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Zamowienia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String Status;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Zamowienia_Ksiazka",
            joinColumns = {@JoinColumn(name = "ID_Zamowienia")},
            inverseJoinColumns = {@JoinColumn(name = "ID_Ksiazka")}
    )
    private Set<Ksiazka> ksiazka = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "ID_Klient")
    private Klient klient;
}
