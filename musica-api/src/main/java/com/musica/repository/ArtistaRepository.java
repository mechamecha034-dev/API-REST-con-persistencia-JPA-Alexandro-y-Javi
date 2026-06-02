package com.example.musica.repository;

import com.example.musica.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    List<Artista> findByNombreContainingIgnoreCase(String nombre);

    List<Artista> findByGeneroMusicalIgnoreCase(String generoMusical);
}