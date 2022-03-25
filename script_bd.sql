//Tabla "GINECOLOGAS"
CREATE TABLE ginecologas (
  idginecologa SERIAL NOT NULL,
  nombres varchar(30) NOT NULL,
  apellidoPaterno varchar(40) NOT NULL,
  apellidoMaterno varchar(40) NOT NULL,
  cedulaProfesional varchar(15) NOT NULL,
  cedulaEspecialista varchar(15) NOT NULL,
  telefono varchar(16) NOT NULL,
  correo varchar(45) NOT NULL UNIQUE,
  clave varchar(45) NOT NULL,
  PRIMARY KEY (idginecologa)
);
//USUARIO DE PRUEBA
INSERT INTO ginecologas (nombres,apellidoPaterno, apellidoMaterno,cedulaProfesional,cedulaEspecialista,telefono,correo,clave) 
VALUES ('Esbeydi','Villa', 'Juan','1234567890','1234567890','1234567890','esbeydi@correo.com','ZXNiZXlkaTEyMw=='); 
//Tabla "CITAS"
CREATE TABLE citas (
  idcitas SERIAL NOT NULL,
  nombre varchar(45) NOT NULL,
  hora varchar(5) NOT NULL,
  dia varchar(2) NOT NULL,
  mes varchar(2) NOT NULL,
  descripcion text NOT NULL,
  idginecologa int NOT NULL,
  estado integer NOT NULL,
  PRIMARY KEY (idcitas)
);
//FK de "GINECOLOGAS" a "CITAS"
ALTER TABLE citas
ADD FOREIGN KEY (idginecologa) REFERENCES ginecologas(idginecologa);
//Tabla "PACIENTES"
CREATE TABLE pacientes (
  idPaciente SERIAL NOT NULL,
  nombres varchar(30) NOT NULL,
  apellidoPaterno varchar(40) NOT NULL,
  apellidoMaterno varchar(40) NOT NULL,
  edad varchar(3) NOT NULL,
  diaN varchar(2) NOT NULL,
  mesN varchar(2) NOT NULL,
  anioN varchar(4) NOT NULL,
  estadoCivil varchar(55) NOT NULL,
  ocupacion varchar(55) NOT NULL,
  escolaridad varchar(55) NOT NULL,
  ciudadDeNacimiento varchar(55) NOT NULL,
  entidadDeNacimiento varchar(55) NOT NULL,
  telefono varchar(16) NOT NULL,
  correo varchar(55) NOT NULL,
  idginecologa INTEGER NOT NULL,
  PRIMARY KEY (idpaciente)
);
//FK de "GINECOLOGAS" a "PACIENTES"
ALTER TABLE pacientes
ADD FOREIGN KEY (idginecologa) REFERENCES ginecologas(idginecologa);
