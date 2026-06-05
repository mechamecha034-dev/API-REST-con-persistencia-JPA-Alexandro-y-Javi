# Checklist final de entrega

## Proyecto

- [x] Proyecto Spring Boot con `pom.xml`.
- [x] Java 17 configurado.
- [x] Base de datos H2 persistente en fichero.
- [x] Entidades JPA con `@Entity`, `@Table`, `@Id` y `@GeneratedValue`.
- [x] Arquitectura por capas: controller, service y repository.
- [x] Los controllers no acceden directamente a repositories.
- [x] Repositories extendiendo `JpaRepository`.

## CRUD

- [x] CRUD completo de artistas.
- [x] CRUD completo de canciones.
- [x] CRUD completo de playlists.
- [x] Uso de `Optional` en busquedas por id.
- [x] Respuestas `200`, `201`, `204` y `404` segun corresponda.

## Modulo A

- [x] Relacion `@ManyToOne` de `Cancion` a `Artista`.
- [x] Relacion `@OneToMany` de `Artista` a `Cancion`.
- [x] `@JoinColumn(name = "artista_id")`.
- [x] `@JsonIgnore` y `@ToString.Exclude` para evitar recursividad.
- [x] Endpoint de relacion: `/api/v1/canciones/artista/{artistaId}`.
- [x] Interfaz web simple en `src/main/resources/static/index.html`.

## Modulo B

- [x] Busquedas con `@RequestParam`.
- [x] Parametros opcionales y valores por defecto.
- [x] Metodos derivados como `findByTituloContainingIgnoreCase`.
- [x] Ordenacion con `sortBy` y `order`.
- [x] SQL visible con `spring.jpa.show-sql=true`.

## Modulo C

- [x] Relacion `@ManyToMany` entre `Playlist` y `Cancion`.
- [x] Tabla intermedia `playlist_canciones`.
- [x] Consulta JPQL con `@Query` para contar canciones por artista.
- [x] Consulta JPQL navegando relaciones para playlists por artista.

## Modulo D

- [x] Spring Security configurado.
- [x] Lecturas `GET` publicas.
- [x] Escrituras `POST`, `PUT`, `DELETE` protegidas con Basic Auth.
- [x] Validacion con `@Valid`, `@NotBlank`, `@NotNull`, `@Size` y `@Min`.
- [x] Manejo global de errores con `@RestControllerAdvice`.

## Antes de subir a GitHub

- [ ] Ejecutar `mvn spring-boot:run`.
- [ ] Probar endpoints con Postman o Thunder Client.
- [ ] Importar `docs/postman-collection.json` en Postman si se quiere ir mas rapido.
- [ ] Entrar en H2 Console y sacar capturas de `ARTISTAS`, `CANCIONES`, `PLAYLISTS` y `PLAYLIST_CANCIONES`.
- [ ] Anadir esas capturas al documento final o entregarlas junto al repositorio.
- [ ] Hacer commits de los dos miembros de la pareja.
- [ ] Subir el repositorio a GitHub.
