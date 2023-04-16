CREATE TABLE clientes (
    client_id SERIAL,
    nome_cliente VARCHAR(255) NOT NULL,
    cpf_cliente VARCHAR(11) NOT NULL,
    salario_cliente FLOAT NOT NULL,
    idade_cliente INT NOT NULL,
    email_cliente VARCHAR(255) NOT NULL,
    cep_cliente VARCHAR(8) NOT NULL,
    numero_residencia_cliente INT NOT NULL,
    complemento_residencia_cliente VARCHAR(255) NOT NULL,

    CONSTRAINT pk_cliente PRIMARY KEY (client_id)
);