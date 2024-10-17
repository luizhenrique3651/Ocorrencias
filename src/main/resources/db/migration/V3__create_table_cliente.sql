CREATE TABLE IF NOT EXISTS ocorrencias.cliente (
    cod_cliente SERIAL PRIMARY KEY,
    nme_cliente VARCHAR(255) NOT NULL,
    dta_nascimento DATE NOT NULL,
    nro_cpf VARCHAR(11) NOT NULL,
    dta_criacao DATE NOT NULL
);
