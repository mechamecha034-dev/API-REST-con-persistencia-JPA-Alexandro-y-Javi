package com.musica.service;

import com.musica.model.Cancion;
import com.musica.model.Playlist;
import com.musica.repository.CancionRepository;
import com.musica.repository.PlaylistRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    private final PlaylistRepository playlistRepository;
    private final CancionRepository cancionRepository;

    public PlaylistService(PlaylistRepository playlistRepository, CancionRepository cancionRepository) {
        this.playlistRepository = playlistRepository;
        this.cancionRepository = cancionRepository;
    }

    public List<Playlist> obtenerTodas() {
        return playlistRepository.findAll();
    }

    public Optional<Playlist> obtenerPorId(Long id) {
        return playlistRepository.findById(id);
    }

    public Playlist guardar(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public Optional<Playlist> actualizar(Long id, Playlist datos) {
        return playlistRepository.findById(id)
                .map(playlist -> {
                    playlist.setNombre(datos.getNombre());
                    playlist.setDescripcion(datos.getDescripcion());
                    playlist.setPublica(datos.getPublica());
                    playlist.setCanciones(datos.getCanciones());
                    return playlistRepository.save(playlist);
                });
    }

    public boolean eliminar(Long id) {
        if (playlistRepository.existsById(id)) {
            playlistRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Playlist> buscar(String nombre, String sortBy, String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        return playlistRepository.findByNombreContainingIgnoreCase(nombre, sort);
    }

    public Optional<Playlist> agregarCancion(Long playlistId, Long cancionId) {
        Optional<Playlist> playlistOptional = playlistRepository.findById(playlistId);
        Optional<Cancion> cancionOptional = cancionRepository.findById(cancionId);

        if (playlistOptional.isEmpty() || cancionOptional.isEmpty()) {
            return Optional.empty();
        }

        Playlist playlist = playlistOptional.get();
        playlist.getCanciones().add(cancionOptional.get());
        return Optional.of(playlistRepository.save(playlist));
    }

    public List<Playlist> buscarPorArtista(Long artistaId) {
        return playlistRepository.buscarPlaylistsConCancionesDeArtista(artistaId);
    }
}
