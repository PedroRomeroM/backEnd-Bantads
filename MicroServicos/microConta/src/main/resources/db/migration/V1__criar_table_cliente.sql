CREATE TABLE tb_cliente(
    id_cliente INTEGER,
    nome_cliente VARCHAR(255),
    cpf_cliente VARCHAR(11),


    CONSTRAINT pk_cliente PRIMARY KEY (id_cliente)
);

CREATE TABLE tb_gerente(
    id_gerente INTEGER,
    nome_gerente VARCHAR(255),
    cpf_gerente VARCHAR(11),

    CONSTRAINT pk_gerente PRIMARY KEY (id_gerente)
);

CREATE TABLE tb_conta(
    id_conta SERIAL,
    limite_conta NUMERIC,
    situacao_conta CHAR(1),
    saldo_conta NUMERIC,
    data_conta DATE,
    id_cliente_conta INTEGER,
    id_gerente_conta INTEGER,

    CONSTRAINT pk_conta PRIMARY KEY (id_conta),
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente_conta) REFERENCES tb_cliente (id_cliente),
    CONSTRAINT fk_gerente FOREIGN KEY (id_gerente_conta) REFERENCES tb_gerente (id_gerente)
);

