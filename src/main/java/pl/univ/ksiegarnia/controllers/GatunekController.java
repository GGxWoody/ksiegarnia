package pl.univ.ksiegarnia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.univ.ksiegarnia.model.Gatunek;
import pl.univ.ksiegarnia.repositories.GatunekRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/gatuneks")
public class GatunekController {

    @Autowired
    private GatunekRepository gatunekRepository;

    @GetMapping
    public Iterable<Gatunek> getAllGatunek() {
        return gatunekRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gatunek> getGatunekById(@PathVariable(value = "id") Long gatunekId)
            throws ResourceNotFoundException {
        Gatunek gatunek =
                gatunekRepository
                        .findById(gatunekId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Gatunku o ID ::" + gatunekId));
        return ResponseEntity.ok(gatunek);
    }

    @PostMapping
    public Gatunek createGatunek(@Valid @RequestBody Gatunek gatunek) {
        return gatunekRepository.save(gatunek);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gatunek> updateGatunek(
            @PathVariable(value = "id") Long gatunekId, @Valid @RequestBody Gatunek gatunek)
            throws ResourceNotFoundException {
        Gatunek gatunekTemp =
                gatunekRepository
                        .findById(gatunekId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Gatunku o ID ::" + gatunekId));
        if (gatunek.getNazwa() != null) gatunekTemp.setNazwa(gatunek.getNazwa());
        if (gatunek.getOpis() != null) gatunekTemp.setOpis(gatunek.getOpis());
        if (!gatunek.getKsiazka().isEmpty()) gatunekTemp.setKsiazka(gatunek.getKsiazka());
        final Gatunek updatedGatunek = gatunekRepository.save(gatunekTemp);
        return ResponseEntity.ok(updatedGatunek);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteGatunek(@PathVariable(value = "id") Long gatunekId)
        throws ResourceNotFoundException{
        Gatunek gatunek =
                gatunekRepository
                        .findById(gatunekId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Gatunku o ID ::" + gatunekId));
        Map<String, Boolean> response = new HashMap<>();
        if (gatunek.getKsiazka().isEmpty()) {
            gatunekRepository.delete(gatunek);
            response.put("deleted", Boolean.TRUE);
            return response;
        } else {
            response.put("deleted", Boolean.FALSE);
            return response;
        }
    }
}
