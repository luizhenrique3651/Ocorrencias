CREATE TABLE IF NOT EXISTS ocorrencias.endereco (
    cod_endereco SERIAL PRIMARY KEY,
    nme_logradouro VARCHAR(255) NOT NULL,
    nme_bairro VARCHAR(255) NOT NULL,
    nro_cep VARCHAR(8) NOT NULL,
    nme_cidade VARCHAR(255) NOT NULL,
    nme_estado VARCHAR(255) NOT NULL
);
