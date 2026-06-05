# Guia rapida para la defensa

## Explicacion por capas

- `Controller`: recibe las peticiones HTTP, valida el cuerpo con `@Valid` y devuelve `ResponseEntity`.
- `Service`: contiene la logica de negocio y decide si se guarda, actualiza, elimina o busca.
- `Repository`: extiende `JpaRepository` y comunica la aplicacion con la base de datos.

El controller no llama nunca directamente al repository. Siempre pasa por el service.

## Optional

`findById` devuelve `Optional<T>` porque puede encontrar un registro o no encontrar nada. En los controladores se usa:

```java
.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build())
```

Asi, si existe devuelve `200 OK`; si no existe devuelve `404 Not Found`.

## Relaciones JPA

- `Cancion` tiene `@ManyToOne` hacia `Artista`: muchas canciones pueden pertenecer a un artista.
- `Artista` tiene `@OneToMany(mappedBy = "artista")`: es el lado inverso de la relacion.
- `Playlist` tiene `@ManyToMany` con `Cancion`: una playlist puede tener muchas canciones y una cancion puede estar en muchas playlists.
- La tabla intermedia se llama `playlist_canciones`.

## JsonIgnore y ToString.Exclude

`@JsonIgnore` evita bucles infinitos al convertir entidades a JSON. Por ejemplo, un artista tiene canciones y cada cancion tiene artista. Si no se corta uno de los lados, el JSON podria repetirse sin fin.

`@ToString.Exclude` evita el mismo problema cuando Lombok genera el metodo `toString`.

## JPQL

JPQL consulta entidades Java, no tablas SQL. Por ejemplo:

```java
@Query("SELECT COUNT(c) FROM Cancion c WHERE c.artista.id = :artistaId")
```

`Cancion` es la clase Java, y `artista.id` navega por la relacion entre entidades.

## Seguridad

Spring Security permite que los `GET` sean publicos. Los endpoints de escritura (`POST`, `PUT`, `DELETE`) piden Basic Auth.

Credenciales:

- Usuario: `admin`
- Password: `admin123`

## Validacion y errores

Las entidades usan anotaciones como `@NotBlank`, `@NotNull`, `@Size` y `@Min`. El controlador activa la validacion con `@Valid`.

`GlobalExceptionHandler` usa `@RestControllerAdvice` para devolver errores JSON claros en vez del error por defecto de Spring.

## Llamadas para mostrar en Postman o Thunder Client

1. `GET http://localhost:8080/api/v1/artistas`
2. `GET http://localhost:8080/api/v1/canciones`
3. `GET http://localhost:8080/api/v1/playlists`
4. `GET http://localhost:8080/api/v1/canciones/artista/1`
5. `GET http://localhost:8080/api/v1/canciones/buscar?titulo=mal&sortBy=duracion&order=desc`
6. `GET http://localhost:8080/api/v1/canciones/artista/1/contador`
7. `GET http://localhost:8080/api/v1/playlists/artista/1`
8. `POST http://localhost:8080/api/v1/artistas` sin auth para mostrar `401`
9. `POST http://localhost:8080/api/v1/artistas` con auth para mostrar `201`
