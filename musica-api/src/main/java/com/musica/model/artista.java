package com.example.musica.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "artistas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String pais;

    private String generoMusical;

    private Integer anioDebut;

    private Boolean activo;
}