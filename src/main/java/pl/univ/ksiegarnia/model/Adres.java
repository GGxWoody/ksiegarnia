package pl.univ.ksiegarnia.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Adres {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    private String Miasto;
    private String Ulica;
    private Integer Nr_Domu;
    private Integer Nr_Mieszkania;

    @ManyToMany(mappedBy = "adres")
    private Set<Klient> klients = new HashSet<>();
}
