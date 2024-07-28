ALTER TABLE clientes
ADD COLUMN rua VARCHAR(255),
ADD COLUMN complemento VARCHAR(255),
ADD COLUMN cidade VARCHAR(100),
ADD COLUMN estado VARCHAR(50),
ADD COLUMN cep VARCHAR(20);

ALTER TABLE intencao_compras DROP FOREIGN KEY intencao_compras_ibfk_1;
ALTER TABLE intencao_compras DROP COLUMN endereco_id;
ALTER TABLE clientes DROP FOREIGN KEY clientes_ibfk_1;

DROP TABLE enderecos;