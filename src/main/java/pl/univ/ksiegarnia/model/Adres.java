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
    private Long id;
    private String miasto;
    private String ulica;
    private Integer nrDomu;
    private Integer nrMieszkania;

    @ManyToMany(mappedBy = "adres")
    private Set<Klient> klients = new HashSet<>();
}
