CREATE TABLE IF NOT EXISTS ocorrencias.ocorrencia (
    cod_ocorrencia SERIAL PRIMARY KEY,
    dat_ocorrencia DATE NOT NULL,
    sta_ocorrencia VARCHAR(50) NOT NULL,
    cod_cliente INTEGER NOT NULL,
    cod_endereco INTEGER NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (cod_cliente) REFERENCES ocorrencias.cliente (cod_cliente) ON DELETE CASCADE,
    CONSTRAINT fk_endereco FOREIGN KEY (cod_endereco) REFERENCES ocorrencias.endereco (cod_endereco) ON DELETE CASCADE
);
