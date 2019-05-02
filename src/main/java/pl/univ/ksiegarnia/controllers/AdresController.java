package pl.univ.ksiegarnia.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.univ.ksiegarnia.model.Adres;
import pl.univ.ksiegarnia.repositories.AdresRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@RestController
public class AdresController {

    @Autowired
    private AdresRepository adresRepository;

    @GetMapping("/adreses")
    public Iterable<Adres> getAllAdresy() {
        return adresRepository.findAll();
    }

    @GetMapping("/adreses/{id}")
    public ResponseEntity<Adres> getAdresById(@PathVariable(value = "id") Long adresId)
            throws ResourceNotFoundException {
        Adres adres =
                adresRepository
                        .findById(adresId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Adresu o ID ::" + adresId));
        return ResponseEntity.ok(adres);
    }

    @PostMapping("/adreses")
    public Adres createAdres(@Valid @RequestBody Adres adres) {
        return adresRepository.save(adres);
    }

    @PutMapping("/adreses/{id}")
    public ResponseEntity<Adres> updateAdres(
            @PathVariable(value = "id") Long adresId, @Valid @RequestBody Adres adres)
            throws ResourceNotFoundException {
        Adres adresTemp =
                adresRepository
                        .findById(adresId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Adresu o ID ::" + adresId));

        if (!adres.getKlients().isEmpty()) adresTemp.setKlients(adres.getKlients());
        if (adres.getMiasto() != null) adresTemp.setMiasto(adres.getMiasto());
        if (adres.getUlica() != null) adresTemp.setUlica(adres.getUlica());
        if (adres.getNrDomu() != null) adresTemp.setNrDomu(adres.getNrDomu());
        if (adres.getNrMieszkania() != null) adresTemp.setNrMieszkania(adres.getNrMieszkania());
        final Adres updatedAdres = adresRepository.save(adresTemp);
        return ResponseEntity.ok(updatedAdres);
    }

    @DeleteMapping("/adreses/{id}")
    public Map<String, Boolean> deleteAdres(@PathVariable(value = "id") Long adresId)
            throws ResourceNotFoundException {
        Adres adres =
                adresRepository
                        .findById(adresId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Adresu o ID ::" + adresId));
        Map<String, Boolean> response = new HashMap<>();
        if (adres.getKlients().isEmpty()) {
            adresRepository.delete(adres);
            response.put("deleted", Boolean.TRUE);
            return response;
        } else {
            response.put("deleted", Boolean.FALSE);
            return response;
        }
    }

}
