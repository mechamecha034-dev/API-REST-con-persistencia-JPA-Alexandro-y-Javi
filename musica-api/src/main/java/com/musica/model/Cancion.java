package com.musica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "canciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cancion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El titulo de la cancion es obligatorio")
    @Size(max = 100, message = "El titulo no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String titulo;

    @NotNull(message = "La cancion debe tener un artista asignado")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artista_id", nullable = false)
    private Artista artista;

    @Size(max = 100, message = "El album no puede superar los 100 caracteres")
    @Column(length = 100)
    private String album;

    @NotNull(message = "La duracion es obligatoria")
    @Min(value = 1, message = "La duracion debe ser de al menos 1 segundo")
    private Integer duracion;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "canciones")
    private Set<Playlist> playlists = new HashSet<>();
}
