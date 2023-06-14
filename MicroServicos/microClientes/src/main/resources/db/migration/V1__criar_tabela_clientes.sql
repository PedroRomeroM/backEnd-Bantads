CREATE TABLE clientes (
    client_id SERIAL,
    nome_cliente VARCHAR(255) NOT NULL,
    email_cliente VARCHAR(255) UNIQUE NOT NULL,
    cpf_cliente VARCHAR(11) UNIQUE NOT NULL,
    telefone_cliente VARCHAR(20) NOT NULL,
    salario_cliente FLOAT NOT NULL,

    tipo_endereco_cliente VARCHAR(255) NOT NULL,
    logradouro_cliente VARCHAR(255) NOT NULL,
    numero_residencia_cliente VARCHAR(20) NOT NULL,
    complemento_residencia_cliente VARCHAR(255) NOT NULL,
    cep_cliente VARCHAR(8) NOT NULL,
    cidade_cliente VARCHAR(50) NOT NULL,
    estado_cliente CHAR(2) NOT NULL,


    CONSTRAINT pk_cliente PRIMARY KEY (client_id)
);