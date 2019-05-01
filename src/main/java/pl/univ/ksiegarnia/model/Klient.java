package pl.univ.ksiegarnia.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String Imie;
    private String Nazwisko;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "Klient_Adres",
            joinColumns = {@JoinColumn(name ="ID_Klient")},
            inverseJoinColumns = {@JoinColumn(name = "ID_Adres")}
    )
    private Set<Adres> adres = new HashSet<>();

    @OneToMany(mappedBy = "klient")
    private Set<Zamowienia> zamowienia = new HashSet<>();
}
