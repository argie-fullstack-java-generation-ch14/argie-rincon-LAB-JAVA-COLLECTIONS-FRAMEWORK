# Laboratorio: Agenda de Contactos (Java Collections Framework)

## 🎯 Objetivo del Proyecto

Aplicar los conceptos fundamentales del **Framework de Colecciones de Java (JCF)** mediante la implementación de una *
*Agenda de Contactos**.

A través de este laboratorio se practican los siguientes temas:

* Uso e implementación de `List`, `Set` y `Map`.
* Declaración orientada a interfaces y uso de Genéricos (`Generics`).
* Comparación entre orden de inserción (`ArrayList`) y orden alfabético (`TreeMap`/`TreeSet`).
* Control de flujo e interactividad por consola mediante `Scanner` con manejo de excepciones y entradas inválidas.

---

## 🛠️ Requerimientos Técnicos e Implementación

### 1. Clase `Contacto`

Representa un contacto individual en la agenda.

* **Atributos privados:**
    * `nombre` (`String`)
    * `telefono` (`String`)
    * `email` (`String`)
* **Métodos:**
    * Constructor con todos los atributos.
    * Métodos `Getters` y `Setters`.
    * Override de `toString()` con el formato: `Nombre | Teléfono: X | Email: Y`.

---

### 2. Clase `Agenda`

Gestiona la lógica principal de contactos, grupos e historial usando las siguientes estructuras de datos:

| Estructura                  | Interfaz / Tipo            | Implementación                     | Justificación Técnica                                                 |
|:----------------------------|:---------------------------|:-----------------------------------|:----------------------------------------------------------------------|
| **Contactos**               | `Map<String, Contacto>`    | `TreeMap`                          | Búsqueda rápida por nombre y orden alfabético garantizado por claves. |
| **Grupos**                  | `Set<String>`              | `TreeSet`                          | Mantiene nombres de grupos únicos organizados alfabéticamente.        |
| **Relación Grupo-Contacto** | `Map<String, Set<String>>` | `TreeMap<String, TreeSet<String>>` | Asocia cada grupo con un conjunto de nombres de contactos.            |
| **Historial**               | `List<Contacto>`           | `ArrayList`                        | Guarda el registro de contactos en el orden exacto de inserción.      |

#### Métodos a Implementar en `Agenda`:

* `agregarContacto(Contacto c)`: Agrega el contacto al `Map` y al historial (`List`). Muestra error si el nombre ya
  existe.
* `buscarContacto(String nombre)`: Retorna el contacto o `null` si no existe.
* `eliminarContacto(String nombre)`: Elimina el contacto del `Map`, de la `List` del historial y de todos los grupos
  registrados.
* `listarContactos()`: Recorre el `TreeMap` mostrando los contactos en orden alfabético.
* `listarHistorial()`: Recorre el `ArrayList` mostrando los contactos según su orden de inserción.
* `agregarGrupo(String nombreGrupo)`: Crea un grupo nuevo en el `Set`. Retorna error si ya existe.
* `agregarContactoAGrupo(String nombreContacto, String nombreGrupo)`: Asocia un contacto existente a un grupo existente.
* `listarGrupos()`: Muestra los grupos ordenados alfabéticamente.
* `mostrarContactosPorGrupo(String nombreGrupo)`: Muestra los contactos pertenecientes a la categoría o un mensaje si
  está vacío/inexistente.

---

### 3. Clase `MenuPrincipal`

Consola interactiva con opciones del menú desplegable:

```text
=== AGENDA DE CONTACTOS ===
1. Agregar contacto
2. Buscar contacto
3. Eliminar contacto
4. Listar contactos (orden alfabético)
5. Listar historial (orden de inserción)
6. Agregar grupo
7. Agregar contacto a grupo
8. Listar grupos
9. Mostrar contactos por grupo
10. Salir
===========================
Elige una opción:
```

#### Reglas de Interacción e Input Handling:

* Bucle continuo `while` que finaliza con la opción `10. Salir`.
* Uso de `switch-case` para la gestión de las opciones.
* Validación con `scanner.hasNextInt()` previa al consumo del buffer.
* Limpieza del salto de línea restante con `scanner.nextLine()`.
* Cierre explícito del recurso `scanner.close()` al salir.

---

## 🧪 Casos de Prueba (Matriz de Verificación)

|   #    | Caso de Prueba                           | Resultado Esperado                                            |
|:------:|:-----------------------------------------|:--------------------------------------------------------------|
| **1**  | Agregar contacto nuevo                   | `"Contacto agregado: X"`                                      |
| **2**  | Agregar contacto duplicado               | `"Error: Ya existe un contacto con el nombre 'X'"`            |
| **3**  | Buscar contacto existente                | Muestra la información completa del contacto                  |
| **4**  | Buscar contacto inexistente              | `"Contacto no encontrado: X"`                                 |
| **5**  | Eliminar contacto existente              | `"Contacto eliminado: X"`                                     |
| **6**  | Eliminar contacto inexistente            | `"Contacto no encontrado: X"`                                 |
| **7**  | Listar contactos                         | Contactos mostrados en **orden alfabético**                   |
| **8**  | Listar historial                         | Contactos mostrados en **orden de inserción**                 |
| **9**  | Agregar grupo nuevo                      | `"Grupo 'X' creado"`                                          |
| **10** | Agregar grupo duplicado                  | `"Error: El grupo 'X' ya existe"`                             |
| **11** | Agregar contacto a grupo existente       | `"Contacto agregado al grupo 'X'"`                            |
| **12** | Agregar contacto inexistente a grupo     | `"Error: El contacto 'X' no existe"`                          |
| **13** | Agregar contacto a grupo inexistente     | `"Error: El grupo 'X' no existe"`                             |
| **14** | Mostrar contactos de grupo con miembros  | Lista detallada de los contactos del grupo                    |
| **15** | Mostrar contactos de grupo vacío         | `"El grupo 'X' no tiene contactos"`                           |
| **16** | Mostrar contactos de grupo inexistente   | `"Error: El grupo 'X' no existe"`                             |
| **17** | Eliminar contacto asociado a grupos      | Se remueve del `Map`, del historial y de los grupos asociados |
| **18** | Ingresar una letra en la opción del menú | `"Opción inválida, intenta de nuevo"`                         |
| **19** | Ingresar un número fuera de rango        | `"Opción inválida, intenta de nuevo"`                         |
| **20** | Seleccionar opción de salida             | `"Saliendo de la agenda..."`                                  |

---

## 💻 Entregables Solicitados

1. **Código fuente (`.java`):**
    * `Contacto.java`
    * `Agenda.java`
    * `MenuPrincipal.java`
2. **Captura de pantalla de la ejecución** que certifique:
    * Registro de al menos 3 contactos.
    * Listado alfabético e historial por inserción.
    * Registro de al menos 2 grupos y asignación de contactos.
    * Búsqueda exitosa y fallida.
    * Eliminación de un contacto.
    * Control de duplicados.

---

## ⚡ Retos Adicionales (Opcional)

Si concluyes antes del tiempo asignado, implementa:

1. **Búsqueda por dominio:** Filtrar y listar contactos según el dominio de correo (ej. `@gmail.com`).
2. **Eliminación de grupos:** Opción para eliminar un grupo completo manteniendo la integridad de los contactos.
3. **Ordenamiento por email:** Método para desplegar los contactos ordenados alfabéticamente según su correo
   electrónico.