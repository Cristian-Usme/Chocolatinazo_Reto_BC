# Requisitos e Historias de Usuario — El Chocolatinazo

---

## Requisitos Funcionales (RF)

### Autenticación
| ID | Descripción |
|----|-------------|
| RF-01 | El sistema debe permitir el registro de usuarios con nombre de usuario, contraseña y rol. |
| RF-02 | El sistema debe validar las credenciales y retornar un token JWT firmado al iniciar sesión. |
| RF-03 | Todos los endpoints del juego deben estar protegidos y requerir un token JWT válido. |
| RF-04 | El token JWT debe incluir el rol del usuario para soportar las decisiones de autorización. |

### Juego — Jugador
| ID | Descripción |
|----|-------------|
| RF-05 | Un jugador debe poder elegir una chocolatina, generando un número aleatorio entre 1 y 320. |
| RF-06 | Cada elección debe registrarse en la tabla del juego actual, asociada al jugador y con marca de tiempo. |
| RF-07 | Un jugador no debe poder elegir más de una chocolatina por ronda. |

### Juego — Auditor
| ID | Descripción |
|----|-------------|
| RF-08 | El auditor debe poder consultar todas las elecciones registradas en el juego actual. |
| RF-09 | El auditor debe poder consultar el listado completo de juegos finalizados con sus detalles. |

### Juego — Administrador
| ID | Descripción |
|----|-------------|
| RF-10 | El administrador debe poder actualizar el precio de mercado actual de una chocolatina. |
| RF-11 | El administrador debe poder calcular el perdedor del juego actual basándose en una regla dada (número mayor o menor). |
| RF-12 | El sistema debe calcular el valor total a pagar por el perdedor (cantidad de chocolatinas × precio actual). |
| RF-13 | Tras calcular el perdedor, todos los registros del juego actual deben eliminarse para permitir una nueva ronda. |
| RF-14 | El juego finalizado debe persistirse con: jugador perdedor, número con que perdió, chocolatinas jugadas, precio de la chocolatina, total pagado y marca de tiempo. |

---

## Requisitos No Funcionales (RNF)

| ID | Categoría | Descripción |
|----|-----------|-------------|
| RNF-01 | Seguridad | Las contraseñas deben almacenarse usando un algoritmo de hashing seguro (ej. BCrypt). |
| RNF-02 | Seguridad | Los tokens JWT deben tener un tiempo de expiración definido. |
| RNF-03 | Seguridad | El sistema debe rechazar solicitudes con tokens expirados o inválidos con una respuesta 401. |
| RNF-04 | Autorización | El acceso a cada endpoint debe estar restringido según el rol del usuario (RBAC). |
| RNF-05 | Autorización | Los intentos de acceso no autorizado deben retornar una respuesta 403 clara. |
| RNF-06 | Mantenibilidad | El código debe seguir los principios de Arquitectura Limpia con separación clara de capas. |
| RNF-07 | Mantenibilidad | Todo el código debe estar escrito en inglés. |
| RNF-08 | Confiabilidad | El sistema debe manejar entradas inválidas y retornar mensajes de error descriptivos. |
| RNF-09 | Confiabilidad | El sistema debe retornar códigos de estado HTTP apropiados para todas las respuestas. |
| RNF-10 | Trazabilidad | Todos los registros del juego deben incluir marcas de tiempo de creación. |

---

## Historias de Usuario

### Autenticación

**HU-01 — Registro de usuario**
> Como nuevo usuario, quiero registrarme en el sistema con mi nombre de usuario, contraseña y rol, para poder acceder a la aplicación.

**Criterios de aceptación:**
- El nombre de usuario debe ser único.
- La contraseña se almacena de forma encriptada.
- El rol debe ser uno de: `PLAYER`, `AUDITOR`, `ADMIN`.
- Retorna `201 Created` con el usuario creado (sin contraseña).

---

**HU-02 — Inicio de sesión**
> Como usuario registrado, quiero iniciar sesión con mis credenciales, para recibir un token JWT que autentique mis solicitudes.

**Criterios de aceptación:**
- Retorna un token JWT firmado con credenciales válidas.
- Retorna `401 Unauthorized` con credenciales inválidas.
- El token incluye el ID del usuario y su rol en el payload.

---

### Jugador

**HU-03 — Elegir una chocolatina**
> Como jugador, quiero elegir una chocolatina, para obtener un número aleatorio asignado a mí en la ronda actual.

**Criterios de aceptación:**
- Genera un número aleatorio entre 1 y 320.
- Registra la elección en la tabla del juego actual con información del jugador y marca de tiempo.
- Un jugador no puede elegir más de una vez por ronda.
- Retorna `403 Forbidden` si el usuario no es un JUGADOR.

---

### Auditor

**HU-04 — Auditar juego actual**
> Como auditor, quiero ver todas las elecciones del juego actual, para monitorear la ronda en progreso.

**Criterios de aceptación:**
- Retorna la lista de todas las elecciones actuales con: jugador, número y marca de tiempo.
- Retorna lista vacía si aún no hay elecciones registradas.
- Retorna `403 Forbidden` si el usuario no es un AUDITOR.

---

**HU-05 — Auditar juegos finalizados**
> Como auditor, quiero consultar el historial de todos los juegos finalizados, para revisar los resultados anteriores.

**Criterios de aceptación:**
- Retorna la lista de juegos finalizados con: jugador perdedor, número con que perdió, chocolatinas jugadas, precio de la chocolatina, total pagado y marca de tiempo.
- Retorna `403 Forbidden` si el usuario no es un AUDITOR.

---

### Administrador

**HU-06 — Actualizar precio de la chocolatina**
> Como administrador, quiero actualizar el precio de mercado actual de una chocolatina, para que el perdedor pague el monto correcto.

**Criterios de aceptación:**
- El precio debe ser un valor positivo.
- Retorna `200 OK` con el precio actualizado.
- Retorna `403 Forbidden` si el usuario no es un ADMINISTRADOR.

---

**HU-07 — Calcular perdedor del juego**
> Como administrador, quiero calcular el perdedor de la ronda actual basándome en una regla (número mayor o menor), para poder concluir el juego.

**Criterios de aceptación:**
- La regla se pasa en el cuerpo de la solicitud: `"rule": "HIGHEST"` o `"rule": "LOWEST"`.
- Identifica al jugador con el número mayor o menor según corresponda.
- Calcula el total a pagar: `chocolatinas jugadas × precio actual`.
- Elimina todos los registros del juego actual tras el cálculo.
- Persiste el resultado en la tabla de juegos finalizados.
- Retorna la información del perdedor y el monto total a pagar.
- Retorna `400 Bad Request` si no hay registros en el juego actual.
- Retorna `403 Forbidden` si el usuario no es un ADMINISTRADOR.
