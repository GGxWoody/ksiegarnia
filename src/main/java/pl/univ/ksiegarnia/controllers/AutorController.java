package pl.univ.ksiegarnia.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.univ.ksiegarnia.model.Autor;
import pl.univ.ksiegarnia.repositories.AutorRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/autors")
public class AutorController {

    @Autowired
    AutorRepository autorRepository;

    @GetMapping
    public Iterable<Autor> getAllAutor() {
        return autorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> getAutorById(@PathVariable(value = "id") Long autorId)
            throws ResourceNotFoundException {
        Autor autor =
                autorRepository
                        .findById(autorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Autora o ID ::" + autorId));
        return ResponseEntity.ok(autor);
    }

    @PostMapping
    public Autor createAutor(@Valid @RequestBody Autor autor) {
        return autorRepository.save(autor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Autor> updateAutor(
            @PathVariable(value = "id") Long autorId, @Valid @RequestBody Autor autor)
            throws ResourceNotFoundException {
        Autor autorTemp =
                autorRepository
                        .findById(autorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Autora o ID ::" + autorId));

        if (autor.getImie() != null) autorTemp.setImie(autor.getImie());
        if (autor.getNazwisko() != null) autorTemp.setNazwisko(autor.getNazwisko());
        if (!autor.getKsiazka().isEmpty()) autorTemp.setKsiazka(autor.getKsiazka());
        final Autor updatedAutor = autorRepository.save(autorTemp);
        return ResponseEntity.ok(updatedAutor);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteAutor(@PathVariable(value = "id") Long autorId)
            throws ResourceNotFoundException {
        Autor autor =
                autorRepository
                        .findById(autorId)
                        .orElseThrow(() -> new ResourceNotFoundException("Brak Autora o ID ::" + autorId));
        Map<String, Boolean> response = new HashMap<>();
        if(autor.getKsiazka().isEmpty()){
            autorRepository.delete(autor);
            response.put("deleted", Boolean.TRUE);
            return response;
        }else{
            response.put("deleted", Boolean.FALSE);
            return response;
        }
    }
}
