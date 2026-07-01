# Arquitectura

## Tipo de aplicación

Monolito modular.

## Stack

- Java 17
- Spring Boot 3
- Spring Security
- JWT
- PostgreSQL
- Docker
- Maven
- JUnit
- Mockito

## Módulos

auth
pokemon
trainer
common
config
exception

## Flujo de captura

Usuario

↓

POST /pokemon/capturar

↓

PokemonService

↓

PokeAPI

↓

Validación

↓

Persistencia PostgreSQL

↓

Respuesta