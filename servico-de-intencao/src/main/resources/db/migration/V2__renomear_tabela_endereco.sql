ALTER TABLE intencao_compras DROP FOREIGN KEY intencao_compras_ibfk_1;
ALTER TABLE clientes DROP FOREIGN KEY clientes_ibfk_1;

RENAME TABLE endereco TO enderecos;

ALTER TABLE intencao_compras
ADD CONSTRAINT intencao_compras_ibfk_1
FOREIGN KEY (endereco_id) REFERENCES enderecos(id);

ALTER TABLE clientes
ADD CONSTRAINT clientes_ibfk_1
FOREIGN KEY (endereco_id) REFERENCES enderecos(id);