CREATE TABLE tb_movimentacoes
(
 data DATE NOT NULL,
 tipo VARCHAR(50),
 cliente int,
 origem int,
 destino int,
 valor FLOAT
);


ALTER TABLE tb_movimentacoes ADD FOREIGN KEY(cliente) REFERENCES tb_Cliente (client_id);
ALTER TABLE tb_movimentacoes ADD FOREIGN KEY(origem) REFERENCES tb_conta (id_conta);
ALTER TABLE tb_movimentacoes ADD FOREIGN KEY(destino) REFERENCES tb_conta (id_conta);