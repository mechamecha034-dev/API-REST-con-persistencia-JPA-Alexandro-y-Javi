package com.example.musica.repository;

import com.example.musica.model.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CancionRepository extends JpaRepository<Cancion, Long> {
    
    List<Cancion> findByTituloContainingIgnoreCase(String titulo);
    
    // Filtro por relación para el Módulo A
    List<Cancion> findByArtistaId(Long artistaId);
}