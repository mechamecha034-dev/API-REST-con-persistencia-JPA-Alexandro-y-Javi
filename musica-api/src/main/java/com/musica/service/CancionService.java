package com.musica.service;

import com.musica.model.Cancion;
import com.musica.repository.CancionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CancionService {

    private final CancionRepository cancionRepository;

    public CancionService(CancionRepository cancionRepository) {
        this.cancionRepository = cancionRepository;
    }

    public List<Cancion> obtenerTodas() {
        return cancionRepository.findAll();
    }

    public Optional<Cancion> obtenerPorId(Long id) {
        return cancionRepository.findById(id);
    }

    public Cancion guardar(Cancion cancion) {
        return cancionRepository.save(cancion);
    }

    public Optional<Cancion> actualizar(Long id, Cancion datos) {
        return cancionRepository.findById(id)
                .map(cancion -> {
                    cancion.setTitulo(datos.getTitulo());
                    cancion.setArtista(datos.getArtista());
                    cancion.setAlbum(datos.getAlbum());
                    cancion.setDuracion(datos.getDuracion());
                    return cancionRepository.save(cancion);
                });
    }

    public boolean eliminar(Long id) {
        return cancionRepository.findById(id)
                .map(cancion -> {
                    cancionRepository.delete(cancion);
                    return true;
                })
                .orElse(false);
    }

    public List<Cancion> buscar(String titulo, String sortBy, String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sort = Sort.by(direction, sortBy);
        return cancionRepository.findByTituloContainingIgnoreCase(titulo, sort);
    }

    public List<Cancion> buscarPorArtista(Long artistaId) {
        return cancionRepository.findByArtistaId(artistaId);
    }

    public Long contarPorArtista(Long artistaId) {
        return cancionRepository.contarCancionesPorArtista(artistaId);
    }
}
