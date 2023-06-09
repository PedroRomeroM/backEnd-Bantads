CREATE TABLE gerentes (
    gerente_id SERIAL ,
    nome_gerente VARCHAR(255) NOT NULL,
    email_gerente VARCHAR(255) NOT NULL,
    cpf_gerente VARCHAR(11) UNIQUE NOT NULL,
    telefone_gerente VARCHAR(20) NOT NULL,

    CONSTRAINT pk_gerente PRIMARY KEY (gerente_id)
);