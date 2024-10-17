CREATE TABLE IF NOT EXISTS usuarios.usuario (
    cod_usuario SERIAL PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);
