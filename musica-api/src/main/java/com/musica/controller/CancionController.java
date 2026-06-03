package com.example.musica.controller;

import com.example.musica.model.Cancion;
import com.example.musica.service.CancionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/canciones")
public class CancionController {

    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @GetMapping
    public ResponseEntity<List<Cancion>> getAll() {
        return ResponseEntity.ok(cancionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> getById(@PathVariable Long id) {
        return cancionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cancion> create(@Valid @RequestBody Cancion cancion) {
        Cancion nuevaCancion = cancionService.save(cancion);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCancion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancion> update(@PathVariable Long id, @Valid @RequestBody Cancion cancion) {
        return cancionService.update(id, cancion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (cancionService.delete(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cancion>> buscar(@RequestParam(required = false, defaultValue = "") String titulo) {
        return ResponseEntity.ok(cancionService.buscarPorTitulo(titulo));
    }

    @GetMapping("/artista/{artistaId}")
    public ResponseEntity<List<Cancion>> getCancionesPorArtista(@PathVariable Long artistaId) {
        return ResponseEntity.ok(cancionService.buscarPorArtista(artistaId));
    }
}