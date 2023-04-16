CREATE TABLE clientes (
    clientId SERIAL,
    nomeCliente VARCHAR(255) NOT NULL,
    cpfCliente VARCHAR(11) NOT NULL,
    salarioCliente FLOAT NOT NULL,
    idadeCliente INT NOT NULL,
    emailCliente VARCHAR(255) NOT NULL,
    cepCliente VARCHAR(8) NOT NULL,
    numeroResidenciaCliente INT NOT NULL,
    complementoResidenciaCliente VARCHAR(255) NOT NULL,

    CONSTRAINT pk_cliente PRIMARY KEY (clientId)
);