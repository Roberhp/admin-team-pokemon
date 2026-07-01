AdminTeamPokemon - AI Development Context

Project Overview

AdminTeamPokemon is a Spring Boot application that allows trainers to manage their Pokémon teams.

The application follows a modular monolith architecture and may evolve into microservices in the future.

The main purpose of the application is to manage captured Pokémon, teams, and storage boxes while enforcing Pokémon-inspired business rules.

⸻

Technology Stack

* Java 17
* Spring Boot 3
* Spring Security
* JWT Authentication
* PostgreSQL
* Maven
* Docker
* JUnit 5
* Mockito
* Swagger/OpenAPI

⸻

Architecture Principles

* Modular Monolith
* SOLID Principles
* Clean Code
* Layered Architecture
* DTO Pattern
* Constructor Injection
* Stateless Services

⸻

Package Structure

com.pokeadmin.adminteampokemon

* auth
* pokemon
* trainer
* common
* config
* exception

Each module should contain:

* controller
* service
* repository
* entity
* dto

⸻

Business Rules

Trainer

* username must be unique
* password must be encrypted using BCrypt

Pokemon Capture

When a trainer captures a Pokémon:

1. Validate the Pokémon exists in PokeAPI.
2. If the trainer has fewer than 6 Pokémon in the team:
    * assign to TEAM
3. Otherwise:
    * assign to STORAGE
4. If storage already contains 30 Pokémon:
    * automatically release the captured Pokémon

Nicknames

* Nickname must be unique per trainer.
* If nickname is not provided:
    * use the original Pokémon name.
* Pokémon can be renamed later.

Team Rules

* Maximum 6 Pokémon in TEAM.
* Maximum 30 Pokémon in STORAGE.

⸻

Database Rules

Primary Keys:

* BIGSERIAL

Foreign Keys:

* Explicitly defined

Unique Constraints:

* username
* (trainer_id, nickname)

⸻

API Design Rules

* Use REST conventions.
* Use plural resources when appropriate.
* Return meaningful HTTP status codes.
* Validate requests using Bean Validation.
* Never expose entities directly.
* Use DTOs for requests and responses.

Example:

Bad:

return TrainerEntity

Good:

return TrainerResponseDTO

⸻

Coding Standards

* Use constructor injection.
* Avoid field injection.
* Avoid business logic inside controllers.
* Keep controllers thin.
* Place business rules inside services.
* Use meaningful variable names.
* Prefer composition over inheritance.
* No static utility classes unless necessary.

⸻

Exception Handling

Use Global Exception Handler.

All errors should return:

{
“code”: “BUSINESS_ERROR”,
“message”: “Description”
}

⸻

Testing Rules

Every service must have unit tests.

Use:

* JUnit 5
* Mockito

Minimum tests:

* Success scenario
* Validation scenario
* Business rule scenario

⸻

AI Assistant Instructions

Before generating code:

1. Explain the proposed solution.
2. Explain affected files.
3. Explain business rules involved.

When generating code:

* Follow existing project structure.
* Follow SOLID principles.
* Avoid unnecessary complexity.
* Generate production-quality code.

Never:

* Skip validation.
* Expose entities directly.
* Place business logic in controllers.
* Create unused abstractions.
