INSERT INTO pokemon_capturado
(
    id_pokemon_capturado,
    id_entrenador,
    num_pokedex,
    nickname,
    location,
    fecha_captura,
    sprite_url
)
VALUES
(1,1,25,'Pikachu','TEAM',NOW(),'https://img/25.png'),
(2,1,1,'Bulbasaur','TEAM',NOW(),'https://img/1.png'),
(3,1,6,'Charizard','TEAM',NOW(),'https://img/6.png'),

(4,1,7,'Squirtle','STORAGE',NOW(),'https://img/7.png'),
(5,1,16,'Pidgey','STORAGE',NOW(),'https://img/16.png'),
(6,1,12,'Butterfree','STORAGE',NOW(),'https://img/12.png'),

(7,2,120,'Staryu','TEAM',NOW(),'https://img/120.png'),
(8,2,54,'Psyduck','TEAM',NOW(),'https://img/54.png');