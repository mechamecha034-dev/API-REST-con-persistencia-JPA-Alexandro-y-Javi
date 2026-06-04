package com.musica.controller;

import com.musica.model.Cancion;
import com.musica.service.CancionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/canciones")
public class CancionController {

    private final CancionService cancionService;

    public CancionController(CancionService cancionService) {
        this.cancionService = cancionService;
    }

    @GetMapping
    public ResponseEntity<List<Cancion>> obtenerTodas() {
        return ResponseEntity.ok(cancionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cancion> obtenerPorId(@PathVariable Long id) {
        return cancionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Cancion> crear(@Valid @RequestBody Cancion cancion) {
        Cancion nueva = cancionService.guardar(cancion);
        return ResponseEntity
                .created(URI.create("/api/v1/canciones/" + nueva.getId()))
                .body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cancion> actualizar(@PathVariable Long id, @Valid @RequestBody Cancion cancion) {
        return cancionService.actualizar(id, cancion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (cancionService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Cancion>> buscar(
            @RequestParam(required = false, defaultValue = "") String titulo,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(cancionService.buscar(titulo, sortBy, order));
    }

    @GetMapping("/artista/{artistaId}")
    public ResponseEntity<List<Cancion>> obtenerPorArtista(@PathVariable Long artistaId) {
        return ResponseEntity.ok(cancionService.buscarPorArtista(artistaId));
    }

    @GetMapping("/artista/{artistaId}/contador")
    public ResponseEntity<Map<String, Long>> contarPorArtista(@PathVariable Long artistaId) {
        return ResponseEntity.ok(Map.of("total", cancionService.contarPorArtista(artistaId)));
    }
}
