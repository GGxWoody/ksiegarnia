package pl.univ.ksiegarnia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Gatunek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nazwa;
    private String opis;

    @OneToMany(mappedBy = "gatunek")
    @JsonIgnore
    private Set<Ksiazka> ksiazka = new HashSet<>();
}
