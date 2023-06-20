INSERT INTO tb_cliente (client_id, nome_cliente, cpf_cliente, salario_cliente, cidade_cliente, estado_cliente)
VALUES (1, 'João Silva', '12345678901', 5000.00, 'São Paulo', 'SP');

INSERT INTO tb_gerente (id_gerente, nome_gerente, cpf_gerente)
VALUES (1, 'Maria Souza', '98765432109');

INSERT INTO tb_conta (id_conta, limite_conta, situacao_conta, saldo_conta, data_conta, id_cliente_conta, id_gerente_conta)
VALUES (1, 10000.00, 'A', 5000.00, '2023-06-18', 1, 1);