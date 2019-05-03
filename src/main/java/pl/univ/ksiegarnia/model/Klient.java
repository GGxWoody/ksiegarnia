package pl.univ.ksiegarnia.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Klient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imie;
    private String nazwisko;

    @ManyToMany
    @JoinTable(
            name = "klient_adres",
            joinColumns = @JoinColumn(name ="id_klient", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_adres",referencedColumnName = "id")
    )
    @JsonIgnore
    private Set<Adres> adres = new HashSet<>();

    @OneToMany(mappedBy = "klient")
    @JsonIgnore
    private Set<Zamowienia> zamowienia = new HashSet<>();
}
