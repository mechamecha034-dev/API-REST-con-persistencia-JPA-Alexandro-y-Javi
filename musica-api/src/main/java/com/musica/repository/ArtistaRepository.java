package com.musica.repository;

import com.musica.model.Artista;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {

    List<Artista> findByNombreContainingIgnoreCase(String nombre, Sort sort);

    List<Artista> findByGeneroMusicalIgnoreCase(String generoMusical, Sort sort);
}
