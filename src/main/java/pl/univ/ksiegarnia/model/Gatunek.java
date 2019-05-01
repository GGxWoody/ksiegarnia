package pl.univ.ksiegarnia.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Gatunek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String Nazwa;
    private String Opis;

    @OneToMany(mappedBy = "gatunek")
    private Set<Ksiazka> ksiazka = new HashSet<>();
}
