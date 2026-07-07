INSERT INTO entrenador (
    id_entrenador,
    username,
    password,
    nombre_entrenador,
    pueblo_origen,
    fecha_registro,
    region
)
VALUES
(
    1,
    'ash',
    '$2a$10$DummyPasswordHash',
    'Ash Ketchum',
    'Pallet Town',
    NOW(),
    'Kanto'
),
(
    2,
    'misty',
    '$2a$10$DummyPasswordHash',
    'Misty',
    'Cerulean City',
    NOW(),
    'Kanto'
);