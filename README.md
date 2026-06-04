# API REST con persistencia JPA - Musica

Proyecto Spring Boot para gestionar artistas, canciones y playlists con persistencia JPA/Hibernate en H2.

## Requisitos

- Java 17
- Maven 3.9 o superior

## Arranque

```bash
cd musica-api
mvn spring-boot:run
```

La API queda disponible en:

- API: `http://localhost:8080/api/v1`
- Interfaz web: `http://localhost:8080`
- Consola H2: `http://localhost:8080/h2-console`

Datos para H2:

- JDBC URL: `jdbc:h2:file:./data/musica-db`
- Usuario: `sa`
- Password: dejar vacio

## Seguridad

Los endpoints `GET` son publicos. Los endpoints de escritura (`POST`, `PUT`, `DELETE`) requieren autenticacion Basic Auth.

- Usuario: `admin`
- Password: `admin123`

## Endpoints principales

### Artistas

- `GET /api/v1/artistas`
- `GET /api/v1/artistas/{id}`
- `POST /api/v1/artistas`
- `PUT /api/v1/artistas/{id}`
- `DELETE /api/v1/artistas/{id}`
- `GET /api/v1/artistas/buscar?nombre=rosa&sortBy=nombre&order=asc`
- `GET /api/v1/artistas/buscar?genero=Pop`

### Canciones

- `GET /api/v1/canciones`
- `GET /api/v1/canciones/{id}`
- `POST /api/v1/canciones`
- `PUT /api/v1/canciones/{id}`
- `DELETE /api/v1/canciones/{id}`
- `GET /api/v1/canciones/buscar?titulo=mal&sortBy=duracion&order=desc`
- `GET /api/v1/canciones/artista/{artistaId}`
- `GET /api/v1/canciones/artista/{artistaId}/contador`

### Playlists

- `GET /api/v1/playlists`
- `GET /api/v1/playlists/{id}`
- `POST /api/v1/playlists`
- `PUT /api/v1/playlists/{id}`
- `DELETE /api/v1/playlists/{id}`
- `GET /api/v1/playlists/buscar?nombre=favoritas`
- `PUT /api/v1/playlists/{playlistId}/canciones/{cancionId}`
- `GET /api/v1/playlists/artista/{artistaId}`

## Ejemplos de JSON

Crear artista:

```json
{
  "nombre": "Aitana",
  "pais": "Espana",
  "generoMusical": "Pop",
  "anioDebut": 2017,
  "activo": true
}
```

Crear cancion:

```json
{
  "titulo": "Las babys",
  "artista": {
    "id": 1
  },
  "album": "Alpha",
  "duracion": 165
}
```

Crear playlist:

```json
{
  "nombre": "Entrenamiento",
  "descripcion": "Canciones con energia",
  "publica": true
}
```

## Documentacion de diseno

El documento de diseno esta en [docs/diseno.md](musica-api/docs/diseno.md).
