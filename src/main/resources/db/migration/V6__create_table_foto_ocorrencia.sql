CREATE TABLE IF NOT EXISTS ocorrencias.foto_ocorrencia (
    cod_foto_ocorrencia SERIAL PRIMARY KEY,
    cod_ocorrencia INTEGER NOT NULL,
    dta_criacao TIMESTAMP NOT NULL,
    dsc_path_bucket VARCHAR(255) NOT NULL,
    dsc_hash VARCHAR(255) NOT NULL,
    CONSTRAINT fk_ocorrencia FOREIGN KEY (cod_ocorrencia) REFERENCES ocorrencias.ocorrencia (cod_ocorrencia) ON DELETE CASCADE
);
