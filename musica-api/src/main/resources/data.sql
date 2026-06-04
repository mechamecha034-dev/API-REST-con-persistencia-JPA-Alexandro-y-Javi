INSERT INTO artistas (id, nombre, pais, genero_musical, anio_debut, activo)
SELECT 1, 'Rosalia', 'Espana', 'Pop', 2013, true
WHERE NOT EXISTS (SELECT 1 FROM artistas WHERE id = 1);

INSERT INTO artistas (id, nombre, pais, genero_musical, anio_debut, activo)
SELECT 2, 'Daft Punk', 'Francia', 'Electronica', 1993, false
WHERE NOT EXISTS (SELECT 1 FROM artistas WHERE id = 2);

INSERT INTO canciones (id, titulo, album, duracion, artista_id)
SELECT 1, 'Malamente', 'El mal querer', 150, 1
WHERE NOT EXISTS (SELECT 1 FROM canciones WHERE id = 1);

INSERT INTO canciones (id, titulo, album, duracion, artista_id)
SELECT 2, 'Get Lucky', 'Random Access Memories', 369, 2
WHERE NOT EXISTS (SELECT 1 FROM canciones WHERE id = 2);

INSERT INTO playlists (id, nombre, descripcion, publica)
SELECT 1, 'Favoritas', 'Canciones destacadas para probar la API', true
WHERE NOT EXISTS (SELECT 1 FROM playlists WHERE id = 1);

INSERT INTO playlist_canciones (playlist_id, cancion_id)
SELECT 1, 1
WHERE NOT EXISTS (SELECT 1 FROM playlist_canciones WHERE playlist_id = 1 AND cancion_id = 1);

INSERT INTO playlist_canciones (playlist_id, cancion_id)
SELECT 1, 2
WHERE NOT EXISTS (SELECT 1 FROM playlist_canciones WHERE playlist_id = 1 AND cancion_id = 2);
