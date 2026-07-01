# Modelo Relacional

##  ENTRENADOR

| Campo | Tipo |
|---------|---------|
| id_entrenador | BIGSERIAL |
| username | VARCHAR(50) |
| password | VARCHAR(255) |
| nombre_entrenador | VARCHAR(100) |
| pueblo_origen | VARCHAR(100) |
| fecha_registro | TIMESTAMP |

Restricciones:

- username único

---

## POKEMON_CAPTURADO  CapturedPokemonEntity

| Campo | Tipo |
|---------|---------|
| id_pokemon_capturado | BIGSERIAL |
| id_entrenador | BIGINT |
| num_pokedex | INTEGER |
| apodo | VARCHAR(50) |
| ubicacion | VARCHAR(20) |
| fecha_captura | TIMESTAMP |

Restricciones:

- FK hacia ENTRENADOR
- UNIQUE(id_entrenador, apodo)

---

## Relación

ENTRENADOR (1) ---- (N) POKEMON_CAPTURADO


