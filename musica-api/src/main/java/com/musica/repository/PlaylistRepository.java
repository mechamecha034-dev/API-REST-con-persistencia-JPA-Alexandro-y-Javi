package com.musica.repository;

import com.musica.model.Playlist;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {

    List<Playlist> findByNombreContainingIgnoreCase(String nombre, Sort sort);

    @Query("SELECT DISTINCT p FROM Playlist p JOIN p.canciones c WHERE c.artista.id = :artistaId")
    List<Playlist> buscarPlaylistsConCancionesDeArtista(@Param("artistaId") Long artistaId);
}
