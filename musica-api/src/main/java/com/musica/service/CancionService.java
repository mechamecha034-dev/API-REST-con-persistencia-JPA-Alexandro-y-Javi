package com.example.musica.service;

import com.example.musica.model.Cancion;
import com.example.musica.repository.CancionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    private final CancionRepository cancionRepository;

    public CancionService(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    public List<Cancion> findAll() {
        return cancionRepository.findAll();
    }

    public Optional<Cancion> findById(Long id) {
        return cancionRepository.findById(id);
    }

    public Cancion save(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public Optional<Cancion> update(Long id, Cancion cancionDetails) {
        return cancionRepository.findById(id).map(cancionExistente -> {
            cancionExistente.setTitulo(cancionDetails.getTitulo());
            cancionExistente.setArtista(cancionDetails.getArtista());
            cancionExistente.setAlbum(cancionDetails.getAlbum());
            cancionExistente.setDuracion(cancionDetails.getDuracion());
            return cancionRepository.save(cancionExistente);
        });
    }

    public boolean delete(Long id) {
        return cancionRepository.findById(id).map(cancion -> {
            cancionRepository.delete(cancion);
            return true;
        }).orElse(false);
    }

    public List<Cancion> buscarPorTitulo(String titulo) {
        return cancionRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Cancion> buscarPorArtista(Long artistaId) {
        return cancionRepository.findByArtistaId(artistaId);
    }
}