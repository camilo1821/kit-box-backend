# Kit-Box Backend

Backend de una plataforma de e-commerce desarrollada con **Spring Boot** y **Java 21**.
Proporciona una API REST completa para la gestión de productos, clientes, pedidos y detalles de pedido.

## 🛠 Tecnologías

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| Java | 21 | Lenguaje de programación |
| Spring Boot | 4.1.2 | Framework principal |
| Spring Web MVC | - | API REST |
| Spring Data JPA | - | Acceso a base de datos |
| Hibernate | - | ORM (Object-Relational Mapping) |
| MySQL | - | Base de datos relacional |
| Gradle | - | Sistema de construcción |

## 📐 Arquitectura

El proyecto sigue una arquitectura en capas (MVC) con patrón DTO:

```
src/main/java/com/kit_box/ecommerce/
├── config/          # Configuración (CORS)
├── controller/      # Capa REST - endpoints de la API
├── dto/             # Data Transfer Objects - intercambio de datos
├── model/           # Entidades JPA - mapeo a tablas
├── repository/      # Acceso a datos - Spring Data JPA
└── service/         # Lógica de negocio
    ├── *Service.java          (interfaces)
    └── *ServiceJPA.java       (implementaciones)
```

### Flujo de datos

```
Cliente HTTP → Controller → Service → Repository → Base de datos
                                 ↓
                            DTO (respuesta JSON)
```

## 🗄 Base de datos

El backend se conecta a una base de datos MySQL. Estas son las tablas y sus relaciones:

- Un **cliente** puede tener muchos **pedidos**.
- Un **pedido** puede tener varios registros en **detalle_pedido**.
- **detalle_pedido** une pedidos con productos y guarda la cantidad y el precio total de cada producto dentro del pedido.
  
## Relación 
<img width="486" height="527" alt="image" src="https://github.com/user-attachments/assets/eb5e81f6-4cdf-429f-85d4-cf1a162d9ab2" />

## 🚀 Cómo ejecutar

### Requisitos
- Java 21 o superior
- MySQL Server corriendo en `localhost:3306`
- Base de datos `db_pruebKit` creada

### Pasos
1. Clonar el repositorio
2. Configurar la conexión a la base de datos en `src/main/resources/application.properties`
3. Ejecutar la aplicación:
```bash
./gradlew bootRun
```
4. La API estará disponible en `http://localhost:8080/api`

## 📡 Endpoints

### Productos (`/api/productos`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/productos` | Crear un producto |
| GET | `/api/productos` | Listar productos (paginado, 12 por página, ordenado por id) |
| GET | `/api/productos/{id}` | Buscar un producto por id |
| GET | `/api/productos/buscar/{nombre}` | Buscar productos por nombre |
| PUT | `/api/productos/{id}` | Actualizar un producto |
| DELETE | `/api/productos/{id}` | Eliminar un producto |

### Clientes (`/api/cliente`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/cliente` | Registrar un cliente |
| GET | `/api/cliente` | Listar todos los clientes |
| GET | `/api/cliente/{id}` | Buscar un cliente por id |
| GET | `/api/cliente/correo/{correo}` | Buscar un cliente por correo |
| PUT | `/api/cliente/{id}` | Actualizar un cliente |
| DELETE | `/api/cliente/{id}` | Eliminar un cliente |

### Pedidos (`/api/pedidos`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/pedidos` | Crear un pedido |
| GET | `/api/pedidos` | Listar pedidos (paginado; acepta `page`, `size`, `sortBy`, `sortDirection`) |
| GET | `/api/pedidos/{id}` | Buscar un pedido por id |
| GET | `/api/pedidos/estado/{estado}` | Listar pedidos por estado |
| GET | `/api/pedidos/fecha/{fecha}` | Listar pedidos por fecha (paginado) |
| PUT | `/api/pedidos/{id}` | Actualizar un pedido |
| DELETE | `/api/pedidos/{id}` | Eliminar un pedido |

### Detalles de pedido (`/api/detalles-pedido`)
| Método | Ruta | Descripción |
|--------|------|-------------|
| POST | `/api/detalles-pedido/pedido/{pedidoId}` | Agregar un detalle a un pedido |
| GET | `/api/detalles-pedido` | Listar todos los detalles |
| GET | `/api/detalles-pedido/{id}` | Buscar un detalle por id |
| GET | `/api/detalles-pedido/pedido/{pedidoId}` | Listar los detalles de un pedido |
| PUT | `/api/detalles-pedido/{id}` | Actualizar la cantidad de un detalle |
| DELETE | `/api/detalles-pedido/{id}` | Eliminar un detalle |

## 📝 Notas de desarrollo

### Convenciones del proyecto
- **Nomenclatura**: Clases en `PascalCase`, métodos y variables en `camelCase`, tablas en `snake_case`.
- **Capas**: Cada entidad tiene su propio `Controller`, `Service` (interfaz + implementación JPA), `Repository` y `DTO`.
- **DTOs**: Se usan para evitar exponer la estructura interna de las entidades y prevenir referencias circulares en JSON.
- **Validación**: Los servicios validan la existencia de entidades relacionadas antes de realizar operaciones.
- **Transaccionalidad**: Las operaciones de escritura están anotadas con `@Transactional` para garantizar la consistencia.

### Base de datos
- La configuración actual usa `spring.jpa.hibernate.ddl-auto=create`, lo que **borra y recrea** las tablas en cada inicio.
- Para producción, cambiar a `update` o `validate` para preservar los datos existentes.

### Recálculo automático de totales
- Al crear, actualizar o eliminar un `DetallePedido`, el sistema recalcula automáticamente el `total_pedido` sumando todos los `precioTotal` de los detalles asociados.
