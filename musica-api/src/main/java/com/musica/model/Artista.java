package com.musica.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @NotBlank(message = "El nombre del artista es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank(message = "El pais del artista es obligatorio")
    @Size(max = 80, message = "El pais no puede superar los 80 caracteres")
    @Column(nullable = false, length = 80)
    private String pais;

    @NotBlank(message = "El genero musical es obligatorio")
    @Size(max = 80, message = "El genero musical no puede superar los 80 caracteres")
    @Column(nullable = false, length = 80)
    private String generoMusical;

    @NotNull(message = "El anio de debut es obligatorio")
    @Min(value = 1900, message = "El anio de debut debe ser mayor o igual que 1900")
    private Integer anioDebut;

    @NotNull(message = "El campo activo es obligatorio")
    private Boolean activo;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Cancion> canciones = new ArrayList<>();
}
