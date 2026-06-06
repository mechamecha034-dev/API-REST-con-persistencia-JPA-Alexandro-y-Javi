REQUIERE:
- Java 17
- Maven 3.9 o superior

ARRANQUE:
cd musica-api
mvn spring-boot:run

La API queda disponible en:
- API: http://localhost:8080/api/v1
- Interfaz web: http://localhost:8080
- Consola H2: http://localhost:8080/h2-console

Datos para H2:

- JDBC URL: jdbc:h2:file:./data/musica-db
- Usuario: sa
- Password: dejar vacio

DOCUMENTACION:
- El documento de diseno y capturas esta en documentacion_proyecto.pdf
