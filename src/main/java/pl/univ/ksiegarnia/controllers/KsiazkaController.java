package pl.univ.ksiegarnia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.univ.ksiegarnia.model.Ksiazka;
import pl.univ.ksiegarnia.repositories.KsiazkaRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ksiazkas")
public class KsiazkaController {
    @Autowired
    private KsiazkaRepository ksiazkaRepository;

    @GetMapping
    public Iterable<Ksiazka> getAllKsiazka() {
        return ksiazkaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ksiazka> getKsiazkaById(@PathVariable(value = "id") Long ksiazkaId)
            throws ResourceNotFoundException {
        Ksiazka ksiazka =
                ksiazkaRepository
                        .findById(ksiazkaId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Ksiazki o ID ::" + ksiazkaId));
        return ResponseEntity.ok(ksiazka);
    }

    @PostMapping
    public Ksiazka createKsiazka(@Valid @RequestBody Ksiazka ksiazka) {
        return ksiazkaRepository.save(ksiazka);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ksiazka> updateKsiazka(
            @PathVariable(value = "id") Long ksiazkaId, @Valid @RequestBody Ksiazka ksiazka)
            throws ResourceNotFoundException {
        Ksiazka ksiazkaTemp =
                ksiazkaRepository
                        .findById(ksiazkaId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Ksiazki o ID ::" + ksiazkaId));
        if (ksiazka.getTytul() != null) ksiazkaTemp.setTytul(ksiazka.getTytul());
        if (ksiazka.getGatunek() != null) ksiazkaTemp.setGatunek(ksiazka.getGatunek());
        if (!ksiazka.getAutor().isEmpty()) ksiazkaTemp.setAutor(ksiazka.getAutor());
        if (!ksiazka.getZamowienia().isEmpty()) ksiazkaTemp.setZamowienia(ksiazka.getZamowienia());
        final Ksiazka updatedKsiazka = ksiazkaRepository.save(ksiazkaTemp);
        return ResponseEntity.ok(updatedKsiazka);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteKsiazka(@PathVariable(value = "id") Long ksiazkaId)
            throws ResourceNotFoundException {
        Ksiazka ksiazka =
                ksiazkaRepository
                        .findById(ksiazkaId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Ksiazki o ID ::" + ksiazkaId));
        Map<String, Boolean> response = new HashMap<>();
        if (ksiazka.getZamowienia().isEmpty()) {
            ksiazkaRepository.delete(ksiazka);
            response.put("deleted", Boolean.TRUE);
            return response;
        } else {
            response.put("deleted", Boolean.FALSE);
            return response;
        }
    }
}
