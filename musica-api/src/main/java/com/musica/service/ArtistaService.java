package com.musica.service;

import com.musica.model.Artista;
import com.musica.repository.ArtistaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaService {

    private final ArtistaRepository artistaRepository;

    public ArtistaService(ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
    }

    public List<Artista> obtenerTodos() {
        return artistaRepository.findAll();
    }

    public Optional<Artista> obtenerPorId(Long id) {
        return artistaRepository.findById(id);
    }

    public Artista guardar(Artista artista) {
        return artistaRepository.save(artista);
    }

    public Optional<Artista> actualizar(Long id, Artista nuevoArtista) {
        return artistaRepository.findById(id)
                .map(artista -> {
                    artista.setNombre(nuevoArtista.getNombre());
                    artista.setPais(nuevoArtista.getPais());
                    artista.setGeneroMusical(nuevoArtista.getGeneroMusical());
                    artista.setAnioDebut(nuevoArtista.getAnioDebut());
                    artista.setActivo(nuevoArtista.getActivo());
                    return artistaRepository.save(artista);
                });
    }

    public boolean eliminar(Long id) {
        if (artistaRepository.existsById(id)) {
            artistaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Artista> buscar(String nombre, String genero, String sortBy, String order) {
        Sort sort = crearSort(sortBy, order);
        if (nombre != null && !nombre.isBlank()) {
            return artistaRepository.findByNombreContainingIgnoreCase(nombre, sort);
        }
        if (genero != null && !genero.isBlank()) {
            return artistaRepository.findByGeneroMusicalIgnoreCase(genero, sort);
        }
        return artistaRepository.findAll(sort);
    }

    private Sort crearSort(String sortBy, String order) {
        Sort.Direction direction = "desc".equalsIgnoreCase(order) ? Sort.Direction.DESC : Sort.Direction.ASC;
        return Sort.by(direction, sortBy);
    }
}
