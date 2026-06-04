package com.musica.controller;

import com.musica.model.Playlist;
import com.musica.service.PlaylistService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @GetMapping
    public ResponseEntity<List<Playlist>> obtenerTodas() {
        return ResponseEntity.ok(playlistService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Playlist> obtenerPorId(@PathVariable Long id) {
        return playlistService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Playlist> crear(@Valid @RequestBody Playlist playlist) {
        Playlist nueva = playlistService.guardar(playlist);
        return ResponseEntity
                .created(URI.create("/api/v1/playlists/" + nueva.getId()))
                .body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Playlist> actualizar(@PathVariable Long id, @Valid @RequestBody Playlist playlist) {
        return playlistService.actualizar(id, playlist)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (playlistService.eliminar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Playlist>> buscar(
            @RequestParam(required = false, defaultValue = "") String nombre,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {
        return ResponseEntity.ok(playlistService.buscar(nombre, sortBy, order));
    }

    @PutMapping("/{playlistId}/canciones/{cancionId}")
    public ResponseEntity<Playlist> agregarCancion(@PathVariable Long playlistId, @PathVariable Long cancionId) {
        return playlistService.agregarCancion(playlistId, cancionId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/artista/{artistaId}")
    public ResponseEntity<List<Playlist>> buscarPorArtista(@PathVariable Long artistaId) {
        return ResponseEntity.ok(playlistService.buscarPorArtista(artistaId));
    }
}
