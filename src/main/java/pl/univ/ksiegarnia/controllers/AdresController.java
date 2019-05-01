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
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on ::" + adresId));
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
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on ::" + adresId));

        adresTemp.setKlients(adres.getKlients());
        adresTemp.setMiasto(adres.getMiasto());
        adresTemp.setUlica(adres.getUlica());
        adresTemp.setNrDomu(adres.getNrDomu());
        adresTemp.setNrMieszkania(adres.getNrMieszkania());
        final Adres updatedAdres = adresRepository.save(adresTemp);
        return ResponseEntity.ok(updatedAdres);
    }

    @DeleteMapping("/adreses/{id}")
    public Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long adresId) throws Exception {
        Adres adres =
                adresRepository
                        .findById(adresId)
                        .orElseThrow(() -> new ResourceNotFoundException("User not found on ::" + adresId));

        adresRepository.delete(adres);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

}
