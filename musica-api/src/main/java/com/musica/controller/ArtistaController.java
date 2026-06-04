package com.musica.controller;

import com.musica.model.Artista;
import com.musica.service.ArtistaService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artistas")
public class ArtistaController {

    private final ArtistaService artistaService;

    public ArtistaController(ArtistaService artistaService) {
        this.artistaService = artistaService;
    }

    @GetMapping
    public ResponseEntity<List<Artista>> obtenerTodos() {
        return ResponseEntity.ok(artistaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Artista> obtenerPorId(@PathVariable Long id) {
        return artistaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Artista> crear(@Valid @RequestBody Artista artista) {
        Artista nuevo = artistaService.guardar(artista);
        return ResponseEntity
                .created(URI.create("/api/v1/artistas/" + nuevo.getId()))
                .body(nuevo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Artista> actualizar(@PathVariable Long id, @Valid @RequestBody Artista artista) {
        return artistaService.actualizar(id, artista)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (artistaService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Artista>> buscar(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String genero,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(artistaService.buscar(nombre, genero, sortBy, order));
    }
}
