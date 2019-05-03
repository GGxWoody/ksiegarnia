package pl.univ.ksiegarnia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.univ.ksiegarnia.model.Zamowienia;
import pl.univ.ksiegarnia.repositories.ZamowieniaRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/zamowienias")
public class ZamowieniaController {

    @Autowired
    ZamowieniaRepository zamowieniaRepository;

    @GetMapping
    public Iterable<Zamowienia> getAllZamowiena() {
        return zamowieniaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zamowienia> getZamowieniaById(@PathVariable(value = "id") Long zamowienieId)
            throws ResourceNotFoundException {
        Zamowienia zamowienia =
                zamowieniaRepository
                        .findById(zamowienieId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Zamowienia o ID ::" + zamowienieId));
        return ResponseEntity.ok(zamowienia);
    }

    @PostMapping
    public Zamowienia createKsiazka(@Valid @RequestBody Zamowienia zamowienia) {
        return zamowieniaRepository.save(zamowienia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zamowienia> updateZamowienia(
            @PathVariable(value = "id") Long ksiazkaId, @Valid @RequestBody Zamowienia zamowienia)
            throws ResourceNotFoundException {
        Zamowienia zamowieniaTemp =
                zamowieniaRepository
                        .findById(ksiazkaId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Ksiazki o ID ::" + ksiazkaId));
        if (zamowienia.getKlient()!=null) zamowieniaTemp.setKlient(zamowienia.getKlient());
        if (zamowienia.getStatus() != null) zamowieniaTemp.setStatus(zamowienia.getStatus());
        if (!zamowienia.getKsiazka().isEmpty()) zamowieniaTemp.setKsiazka(zamowienia.getKsiazka());
        final Zamowienia updatedZamowienia = zamowieniaRepository.save(zamowieniaTemp);
        return ResponseEntity.ok(updatedZamowienia);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteZamowienia(@PathVariable(value = "id") Long zamowieniaId)
            throws ResourceNotFoundException {
        Zamowienia zamowienia =
                zamowieniaRepository
                        .findById(zamowieniaId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Ksiazki o ID ::" + zamowieniaId));
        Map<String, Boolean> response = new HashMap<>();
        if (zamowienia.getKsiazka().isEmpty()) {
            zamowieniaRepository.delete(zamowienia);
            response.put("deleted", Boolean.TRUE);
            return response;
        } else {
            response.put("deleted", Boolean.FALSE);
            return response;
        }
    }
}
