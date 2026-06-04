package com.musica.repository;

import com.musica.model.Cancion;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CancionRepository extends JpaRepository<Cancion, Long> {

    List<Cancion> findByTituloContainingIgnoreCase(String titulo, Sort sort);

    List<Cancion> findByArtistaId(Long artistaId);

    @Query("SELECT COUNT(c) FROM Cancion c WHERE c.artista.id = :artistaId")
    Long contarCancionesPorArtista(@Param("artistaId") Long artistaId);
}
