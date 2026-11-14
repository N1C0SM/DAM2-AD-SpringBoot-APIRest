INSERT INTO cliente (codigo, nombre, email, activo, tipo) VALUES ('CLI-001', 'Ana Garcia', 'ana@mail.com', TRUE, 'PARTICULAR');
INSERT INTO cliente (codigo, nombre, email, activo, tipo) VALUES ('CLI-002', 'Tecnologias SL', 'info@tecno.com', TRUE, 'EMPRESA');
INSERT INTO cliente (codigo, nombre, email, activo, tipo) VALUES ('CLI-003', 'Luis Perez', 'luis@mail.com', FALSE, 'PARTICULAR');
INSERT INTO cliente (codigo, nombre, email, activo, tipo) VALUES ('CLI-004', 'Distribuciones SA', 'contacto@dist.com', TRUE, 'EMPRESA');
INSERT INTO pedido (fecha, descripcion, importe, cliente_id) VALUES ('2026-10-01', 'Portatil', 899.99, 1);
INSERT INTO pedido (fecha, descripcion, importe, cliente_id) VALUES ('2026-10-15', 'Raton', 25.50, 1);
INSERT INTO pedido (fecha, descripcion, importe, cliente_id) VALUES ('2026-10-20', 'Licencias software', 1500.00, 2);
