-- Tablas de la practica API REST (clientes y pedidos)
CREATE DATABASE IF NOT EXISTS dam2_apirest;
USE dam2_apirest;

DROP TABLE IF EXISTS pedido;
DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(20) UNIQUE,
    nombre VARCHAR(100),
    email VARCHAR(120),
    activo BOOLEAN,
    tipo VARCHAR(20)
);

-- relacion 1:N, cada pedido tiene su cliente
CREATE TABLE pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATE,
    descripcion VARCHAR(200),
    importe DOUBLE,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES cliente(id)
);

INSERT INTO cliente (codigo, nombre, email, activo, tipo) VALUES
('CLI-001', 'Ana Garcia', 'ana@mail.com', TRUE, 'PARTICULAR'),
('CLI-002', 'Tecnologias SL', 'info@tecno.com', TRUE, 'EMPRESA'),
('CLI-003', 'Luis Perez', 'luis@mail.com', FALSE, 'PARTICULAR'),
('CLI-004', 'Distribuciones SA', 'contacto@dist.com', TRUE, 'EMPRESA');

INSERT INTO pedido (fecha, descripcion, importe, cliente_id) VALUES
('2026-10-01', 'Portatil', 899.99, 1),
('2026-10-15', 'Raton', 25.50, 1),
('2026-10-20', 'Licencias software', 1500.00, 2);

SELECT * FROM cliente;
SELECT * FROM pedido;
