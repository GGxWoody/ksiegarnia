package pl.univ.ksiegarnia.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "klient_adres",
            joinColumns = @JoinColumn(name ="id_adres", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "id_klient",referencedColumnName = "id")
    )
    @JsonIgnore
    private Set<Klient> klients = new HashSet<>();
}
