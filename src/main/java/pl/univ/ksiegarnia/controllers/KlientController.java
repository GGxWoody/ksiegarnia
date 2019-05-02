package pl.univ.ksiegarnia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.univ.ksiegarnia.model.Adres;
import pl.univ.ksiegarnia.model.Klient;
import pl.univ.ksiegarnia.repositories.AdresRepository;
import pl.univ.ksiegarnia.repositories.KlientRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class KlientController {

    @Autowired
    private KlientRepository klientRepository;

    @GetMapping("/klients")
    public Iterable<Klient> getAllKlient() {
        return klientRepository.findAll();
    }

    @GetMapping("/klients/{id}")
    public ResponseEntity<Klient> getKlientById(@PathVariable(value = "id") Long klientId)
            throws ResourceNotFoundException {
        Klient klient =
                klientRepository
                        .findById(klientId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Klienta o ID ::" + klientId));
        return ResponseEntity.ok(klient);
    }

    @PostMapping("/klients")
    public Klient createKlient(@Valid @RequestBody Klient klient) {
        return klientRepository.save(klient);
    }

    @PutMapping("/klients/{id}")
    public ResponseEntity<Klient> updateKlient(
            @PathVariable(value = "id") Long klientId, @Valid @RequestBody Klient klient)
            throws ResourceNotFoundException {
        Klient klientTemp =
                klientRepository
                        .findById(klientId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Klienta o ID ::" + klientId));
        if (klient.getImie() != null) klientTemp.setImie(klient.getImie());
        if (klient.getNazwisko() != null) klientTemp.setNazwisko(klient.getNazwisko());
        if (!klient.getAdres().isEmpty()) klientTemp.setAdres(klient.getAdres());
        if (!klient.getZamowienia().isEmpty()) klientTemp.setZamowienia(klient.getZamowienia());
        final Klient updatedKlient = klientRepository.save(klientTemp);
        return ResponseEntity.ok(updatedKlient);
    }

    @DeleteMapping("/klients/{id}")
    public Map<String, Boolean> deleteKlient(@PathVariable(value = "id") Long klientId)
            throws ResourceNotFoundException {
        Klient klient =
                klientRepository
                        .findById(klientId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Klienta o ID ::" + klientId));
        Map<String, Boolean> response = new HashMap<>();
        if (klient.getZamowienia().isEmpty()) {
            klientRepository.delete(klient);
            response.put("deleted", Boolean.TRUE);
            return response;
        } else {
            response.put("deleted", Boolean.FALSE);
            return response;
        }
    }
}
