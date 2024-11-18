# **Gestor de Coleccionables**

Este proyecto es una API diseñada para gestionar coleccionables como cartas, figuras, y otros objetos de colección. Desarrollada como parte de un proyecto universitario, utiliza **Spring Boot** y una base de datos en memoria **H2** para su funcionamiento.

## **Características**

- Gestión de coleccionables (Cartas y Figuras).
- CRUD completo para coleccionables.
- Funcionalidad para filtrar cartas comerciables.
- Descripción detallada de cada coleccionable.
- Persistencia en base de datos H2.

## **Tecnologías Utilizadas**

- **Java 17**
- **Spring Boot 3.x**
- **H2 Database** (base de datos en memoria para pruebas rápidas)
- **Maven** (gestión de dependencias)

## **Estructura del Proyecto**

1. **Modelos (`model`)**: Representan las entidades principales como `Card`, `Figure`, `Review`, y `User`.
2. **Repositorios (`repository`)**: Interfaz para interactuar con la base de datos.
3. **Controladores (`controller`)**: Gestionan las operaciones de la API REST.
4. **Servicios (`service`)** *(opcional si decides añadir lógica de negocio adicional)*.

## **Cómo Ejecutar**

1. Clona este repositorio:
   ```bash
   git clone <URL-del-repositorio>
   cd <nombre-del-proyecto>
3. Ejecuta el proyecto con:
   ```bash
   mvn spring-boot:run

¡Entendido! Aquí tienes el contenido exactamente como lo necesitas, listo para copiar y pegar en el archivo `README.md`:

```markdown
3. Ejecuta el proyecto con:

   ```bash
   mvn spring-boot:run
   ```

4. Accede a la base de datos H2 en:

   ```
   http://localhost:8080/h2-console
   ```

   - **JDBC URL**: `jdbc:h2:mem:testdb`
   - **Usuario**: `sa`
   - **Contraseña**: *(dejar vacío)*

5. Prueba la API REST con herramientas como Postman o curl.

## **Endpoints ejemplos**

| Método | Endpoint                     | Descripción                                |
|--------|-------------------------------|--------------------------------------------|
| GET    | `/cards`                     | Obtiene todas las cartas.                  |
| POST   | `/cards`                     | Crea una nueva carta.                      |
| GET    | `/cards/{id}`                | Obtiene los detalles de una carta por ID.  |
| PUT    | `/cards/{id}`                | Actualiza una carta existente.             |
| DELETE | `/cards/{id}`                | Elimina una carta por ID.                  |
| GET    | `/cards/tradable`            | Lista las cartas elegibles para comerciar. |
| GET    | `/cards/{id}/trade-description` | Obtiene la descripción de comercio de una carta. |

## **Ejemplo de Petición**

### **Crear una Carta**
```json
POST /cards
Content-Type: application/json

{
  "name": "Blue-Eyes White Dragon",
  "series": "Yu-Gi-Oh",
  "rarity": "Legendary",
  "releaseYear": 2000
}
```

### **Respuesta**
```json
{
  "id": 1,
  "name": "Blue-Eyes White Dragon",
  "series": "Yu-Gi-Oh",
  "rarity": "Legendary",
  "releaseYear": 2000
}
```

---

## **Autor**

**Valentina Gottelli**  
**Sebastian Nallar**  
**Madary  Fernandez**  
**Octavio Emanuel**  


