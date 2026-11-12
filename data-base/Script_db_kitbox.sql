-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema db_kitbox
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema db_kitbox
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `db_kitbox` DEFAULT CHARACTER SET utf8 ;
USE `db_kitbox` ;

-- -----------------------------------------------------
-- Table `db_kitbox`.`cliente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_kitbox`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `apellido` VARCHAR(100) NOT NULL,
  `correo` VARCHAR(255) NOT NULL,
  `telefono` VARCHAR(13) NOT NULL,
  `contrasenia` VARCHAR(45) NOT NULL,
  `rol` VARCHAR(45) NOT NULL,
  `direccion` VARCHAR(100) NOT NULL,
  `fecha_registro` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));


-- -----------------------------------------------------
-- Table `db_kitbox`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_kitbox`.`pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `total_pedido` DOUBLE NOT NULL,
  `direccion_entrega` VARCHAR(100) NOT NULL,
  `fecha_pedido` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cliente_id` INT NOT NULL,
  PRIMARY KEY (`id`, `cliente_id`),
  INDEX `fk_pedido_cliente1_idx` (`cliente_id` ASC) VISIBLE,
  CONSTRAINT `fk_pedido_cliente1`
    FOREIGN KEY (`cliente_id`)
    REFERENCES `db_kitbox`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table `db_kitbox`.`producto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_kitbox`.`producto` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(255) NOT NULL,
  `descripcion` VARCHAR(300) NOT NULL,
  `precio` DOUBLE NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  `stock` INT NOT NULL,
  `imagen` VARCHAR(400) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `db_kitbox`.`detalle_pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `db_kitbox`.`detalle_pedido` (
  `producto_id` INT NOT NULL,
  `pedido_id` INT NOT NULL,
  `cantidad` INT NOT NULL,
  `precio_total` DOUBLE NOT NULL,
  PRIMARY KEY (`producto_id`, `pedido_id`),
  INDEX `fk_producto_has_pedido_pedido1_idx` (`pedido_id` ASC) VISIBLE,
  INDEX `fk_producto_has_pedido_producto1_idx` (`producto_id` ASC) VISIBLE,
  CONSTRAINT `fk_producto_has_pedido_producto1`
    FOREIGN KEY (`producto_id`)
    REFERENCES `db_kitbox`.`producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_producto_has_pedido_pedido1`
    FOREIGN KEY (`pedido_id`)
    REFERENCES `db_kitbox`.`pedido` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;




-- Insersion de datos

INSERT INTO cliente
(nombre, apellido, correo, telefono, contrasenia, rol, direccion)
VALUES
('Cristian', 'Rojas', 'cristian@gmail.com', '3001234567', 'hash_demo_001', 'Administrador', 'Bogota'),
('Laura', 'Martinez', 'laura@gmail.com', '3102345678', 'hash_demo_002', 'Cliente', 'Medellin'),
('Andres', 'Gomez', 'andres@gmail.com', '3203456789', 'hash_demo_003', 'Cliente', 'Cali'),
('Camila', 'Torres', 'camila@gmail.com', '3154567890', 'hash_demo_004', 'Cliente', 'Barranquilla'),
('Daniel', 'Perez', 'daniel@gmail.com', '3015678901', 'hash_demo_005', 'Cliente', 'Bucaramanga');


SELECT * FROM cliente;



INSERT INTO producto
(nombre, descripcion, precio, categoria, stock, imagen)
VALUES
('Kit de herramientas basico', 'Kit de herramientas manuales para el hogar', 85000, 'Herramientas manuales', 20, 'src/assets/imagenes/kit_basico.jpg'),

('Taladro electrico', 'Taladro electrico de uso domestico', 180000, 'Herramientas electricas', 15, 'src/assets/imagenes/taladro.jpg'),

('Juego de destornilladores', 'Set de destornilladores de diferentes tamanos', 45000, 'Herramientas manuales', 30, 'src/assets/imagenes/destornilladores.jpg'),

('Caja organizadora', 'Caja para almacenar herramientas y accesorios', 65000, 'Almacenamiento', 12, 'src/assets/imagenes/caja.jpg'),

('Llave inglesa', 'Llave inglesa ajustable de acero', 35000, 'Herramientas manuales', 25, 'src/assets/imagenes/llave_inglesa.jpg');

SELECT * FROM producto;




INSERT INTO pedido
(total_pedido, direccion_entrega, cliente_id)
VALUES
(130000, 'Bogota - Calle 80', 1),
(180000, 'Medellin - Laureles', 2),
(90000, 'Cali - San Fernando', 3),
(130000, 'Barranquilla - Centro', 4),
(70000, 'Bucaramanga - Cabecera', 5);

select * from pedido;


INSERT INTO detalle_pedido
(producto_id, pedido_id, cantidad, precio_total)
VALUES
(1, 1, 1, 85000),
(2, 2, 1, 180000),
(3, 3, 2, 90000),
(4, 4, 2, 130000),
(5, 5, 2, 70000);


select * from detalle_pedido;

