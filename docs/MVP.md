# AdminTeamPokemon - MVP

## Objetivo

Aplicación para administrar equipos Pokémon de entrenadores.

## Funcionalidades

### Seguridad

- Registro de entrenadores.
- Login mediante JWT.

### Gestión de Pokémon

- Capturar Pokémon.
- Liberar Pokémon.
- Renombrar Pokémon.
- Consultar equipo.
- Consultar resguardo.

### Reglas de negocio

- Máximo 6 Pokémon en equipo.
- Máximo 30 Pokémon en resguardo.
- Si el equipo está lleno, el Pokémon va al resguardo.
- Si el resguardo está lleno, la captura se libera automáticamente.
- El apodo debe ser único para cada entrenador.
- Si no se proporciona apodo, se utilizará el nombre original del Pokémon.