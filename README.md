# kit-box-backend

## Tecnologías
- Spring Boot
- Spring Web
- MySQL Driver
- Spring Data JPA

## Base de datos
El backend se conecta a una base de datos MySQL. Estas son las tablas y sus relaciones:

- Un **cliente** puede tener muchos **pedidos**.
- Un **pedido** puede tener varios registros en **detalle_pedido**.
- **detalle_pedido** une pedidos con productos y guarda la cantidad y el precio total de cada producto dentro del pedido.
  
## Relación 
<img width="486" height="527" alt="image" src="https://github.com/user-attachments/assets/eb5e81f6-4cdf-429f-85d4-cf1a162d9ab2" />


## Endpoints

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
