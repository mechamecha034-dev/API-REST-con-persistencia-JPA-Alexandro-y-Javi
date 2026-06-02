package com.example.musica.service;

import com.example.musica.model.Artista;
import com.example.musica.repository.ArtistaRepository;
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

    public List<Artista> buscarPorNombre(String nombre) {
        return artistaRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Artista> buscarPorGenero(String genero) {
        return artistaRepository.findByGeneroMusicalIgnoreCase(genero);
    }
}