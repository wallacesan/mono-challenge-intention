CREATE TABLE IF NOT EXISTS endereco (
    id INT AUTO_INCREMENT PRIMARY KEY,
    rua VARCHAR(255),
    complemento VARCHAR(255),
    cidade VARCHAR(100),
    estado VARCHAR(50),
    cep VARCHAR(20)
);


CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cpf VARCHAR(20) UNIQUE NOT NULL,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100),
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

CREATE TABLE IF NOT EXISTS intencao_compras (
    id INT AUTO_INCREMENT PRIMARY KEY,
    client_id INT,
    endereco_id INT,
    product_id VARCHAR(255),
    FOREIGN KEY (endereco_id) REFERENCES endereco(id),
    FOREIGN KEY (client_id) REFERENCES clientes(id)
);