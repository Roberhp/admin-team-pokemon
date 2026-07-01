# API Contracts

## Registro

### Endpoint

`POST /api/v1/auth/register`

### Request

```json
{
  "username": "ash",
  "password": "pikachu123",
  "trainerName": "Ash Ketchum",
  "originTown": "Pallet Town"
}
```

### Response

**201 Created**

```json
{
  "trainerId": 1
}
```

### Error Response

**409 Conflict**

```json
{
  "code": "TRAINER_ALREADY_EXISTS",
  "message": "Username already exists"
}
```

---

## Login

### Endpoint

`POST /api/v1/auth/login`

### Request

```json
{
  "username": "ash",
  "password": "pikachu123"
}
```

### Response

**200 OK**

```json
{
  "token": "jwt-token",
  "expiration": 3600000
}
```

### Error Response

**401 Unauthorized**

```json
{
  "code": "INVALID_CREDENTIALS",
  "message": "Invalid credentials"
}
```

---

## Capturar Pokémon

### Endpoint

`POST /capture/try-capture`

### Request

```json
{
  "pokedexNumber": 25,
  "nickname": "Sparky"
}
```

### Request without nickname

```json
{
  "pokedexNumber": 25
}
```

### Response

**201 Created**

```json
{
  "nickname": "Sparky",
  "location": "TEAM"
}
```

### Error Response

**404 Not Found**

```json
{
  "code": "POKEMON_NOT_FOUND",
  "message": "Pokemon does not exist"
}
```

### Error Response

**409 Conflict**

```json
{
  "code": "NICKNAME_ALREADY_EXISTS",
  "message": "Nickname already exists"
}
```

---

## Liberar Pokémon

### Endpoint

`DELETE /admin/pokemon/free/{capturedPokemonId}`

### Response

**200 OK**

```json
{
  "message": "Pokemon released successfully"
}
```

### Error Response

**404 Not Found**

```json
{
  "code": "POKEMON_CAPTURED_NOT_FOUND",
  "message": "Captured Pokemon not found"
}
```

---

## Renombrar Pokémon

### Endpoint

`PUT /admin/pokemon/rename/{capturedPokemonId}`

### Request

```json
{
  "newName": "Thor"
}
```

### Response

**200 OK**

```json
{
  "message": "Pokemon renamed successfully"
}
```

### Error Response

**409 Conflict**

```json
{
  "code": "NICKNAME_ALREADY_EXISTS",
  "message": "Nickname already exists"
}
```

---

## Mover Pokémon

### Endpoint

`PUT /admin/pokemon/{capturedPokemonId}/location`

### Request

```json
{
  "location": "TEAM"
}
```

### Response

**200 OK**

```json
{
  "capturedPokemonId": 1,
  "location": "TEAM"
}
```

### Error Response

**409 Conflict**

```json
{
  "code": "TEAM_IS_FULL",
  "message": "Team already contains six Pokemon"
}
```

---

## Consultar Equipo

### Endpoint

`GET /api/pokemon/team`

### Response

**200 OK**

```json
[
  {
    "capturedPokemonId": 1,
    "pokedexNumber": 25,
    "nickname": "Sparky"
  }
]
```

---

## Consultar Resguardo

### Endpoint

`GET /api/pokemon/storage`

### Response

**200 OK**

```json
[
  {
    "capturedPokemonId": 2,
    "pokedexNumber": 1,
    "nickname": "Bulbasaur"
  }
]
```

---

## Consultar Resumen

### Endpoint

`GET /api/pokemon/summary`

### Response

**200 OK**

```json
{
  "totalTeam": 6,
  "totalStorage": 3,
  "total": 9
}
```
