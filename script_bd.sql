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
  nombre text NOT NULL,
  hora text NOT NULL,
  dia text NOT NULL,
  mes text NOT NULL,
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
//Tabla "DOMICILIOS"
CREATE TABLE domicilios (
  idDomicilio SERIAL NOT NULL,
  municipio varchar(55) NOT NULL,
  colonia varchar(55) NOT NULL,
  calle varchar(55) NOT NULL,
  numero varchar(5) NOT NULL,
  CP varchar(10) NOT NULL,
  idPaciente INTEGER NOT NULL,
  PRIMARY KEY (idDomicilio)
);
//FK de "PACIENTES" a "DOMICILIOS"
ALTER TABLE domicilios
ADD FOREIGN KEY (idPaciente) REFERENCES pacientes(idPaciente);

//Tabla "HISTORIAS CLINICAS"
CREATE TABLE historiasClinicas (
  	idHistoriaClinica SERIAL NOT NULL,
	  fecha date NOT NULL,
  	hora time NOT NULL,
  	idPaciente int NOT NULL,
  	PRIMARY KEY (idHistoriaClinica)
);
//FK de "PACIENTES" a "HISTORIAS CLINICAS"
ALTER TABLE historiasClinicas
ADD FOREIGN KEY (idPaciente) REFERENCES pacientes(idPaciente);
//Tabla "ANTECEDENTES HEREDO FAMILIARES"
CREATE TABLE antecedentesHeredoFamiliares (
  	idAntecedentesHeredoFamiliares SERIAL NOT NULL,
	  diabetes int NOT NULL, 
	  hta int NOT NULL, 
	  neoplasticos int NOT NULL, 
	  cardiopatias int NOT NULL, 
	  tiroides int NOT NULL, 
	  especificaciones text NULL, 
  	idHistoriaClinica int NOT NULL,
  	PRIMARY KEY (idAntecedentesHeredoFamiliares)
);
//FK de "ANTECEDENTES HEREDO FAMILIARES" a "HISTORIAS CLINICAS"
ALTER TABLE antecedentesHeredoFamiliares
ADD FOREIGN KEY (idHistoriaClinica) REFERENCES historiasClinicas(idHistoriaClinica);
//Tabla "ANTECEDENTES PERSONALES PATOLOGICOS"
CREATE TABLE antecedentesPersonalesPatologicos (
  		idAntecedentesPersonalesPatologicos SERIAL NOT NULL,
	  	diabetes int NOT NULL, 
	  	hta int NOT NULL, 
	  	nefropatias int NOT NULL, 
	  	cardiopatias int NOT NULL, 
	  	epilepsia int NOT NULL, 
  		idHistoriaClinica int NOT NULL,
  		PRIMARY KEY (idAntecedentesPersonalesPatologicos)
);
//FK de "ANTECEDENTES PERSONALES PATOLOGICOS" a "HISTORIAS CLINICAS"
ALTER TABLE antecedentesPersonalesPatologicos
ADD FOREIGN KEY (idHistoriaClinica) REFERENCES historiasClinicas(idHistoriaClinica);
//Tabla "ALERGIAS"
CREATE TABLE alergias (
  		idAlergia SERIAL NOT NULL,
	  	nombre text NOT NULL, 
  		idHistoriaClinica int NOT NULL,
  		PRIMARY KEY (idAlergia)
);
//FK de "ALERGIAS" a "HISTORIAS CLINICAS"
ALTER TABLE alergias
ADD FOREIGN KEY (idHistoriaClinica) REFERENCES historiasClinicas(idHistoriaClinica);
//Tabla "ANTECEDENTES TRAUMATICOS"
CREATE TABLE antecedentesTraumaticos (
  		idAntecedenteTraumatico SERIAL NOT NULL,
	  	descripcion text NOT NULL, 
		fecha date NOT NULL,
  		idHistoriaClinica int NOT NULL,
  		PRIMARY KEY (idAntecedenteTraumatico)
);
//FK de "ANTECEDENTES TRAUMATICOS" a "HISTORIAS CLINICAS"
ALTER TABLE antecedentesTraumaticos
ADD FOREIGN KEY (idHistoriaClinica) REFERENCES historiasClinicas(idHistoriaClinica);
//Tabla "ANTECEDENTES QUIRURGICOS"
CREATE TABLE antecedentesQuirurgicos (
  		idAntecedenteQuirurgico SERIAL NOT NULL,
	  	descripcion text NOT NULL, 
		fecha date NOT NULL,
  		idHistoriaClinica int NOT NULL,
  		PRIMARY KEY (idAntecedenteQuirurgico)
);
//FK de "ANTECEDENTES QUIRURGICOS" a "HISTORIAS CLINICAS"
ALTER TABLE antecedentesQuirurgicos
ADD FOREIGN KEY (idHistoriaClinica) REFERENCES historiasClinicas(idHistoriaClinica);
//Tabla "ANTECEDENTES PERSONALES NO PATOLOGICOS"
CREATE TABLE antecedentesPersonalesNoPatologicos (
  		idAntecedentesPersonalesNoPatologicos SERIAL NOT NULL,
	  	tabaquismo int NOT NULL, 
	  	alcoholismo int NOT NULL, 
		drogas int NOT NULL, 
		rubeola int NOT NULL, 
		influenza int NOT NULL,
		tetanos int NOT NULL,
		covid19 int NOT NULL,
  		idHistoriaClinica int NOT NULL,
  		PRIMARY KEY (idAntecedentesPersonalesNoPatologicos)
);
//FK de "ANTECEDENTES PERSONALES NO PATOLOGICOS" a "HISTORIAS CLINICAS"
ALTER TABLE antecedentesPersonalesNoPatologicos
ADD FOREIGN KEY (idHistoriaClinica) REFERENCES historiasClinicas(idHistoriaClinica);
//Tabla "ANTECEDENTES GINECO OBSTETRICOS"
CREATE TABLE antecedentesGinecoObstetricos (
  		idAantecedentesGinecoObstetricos SERIAL NOT NULL,
	  	menarca text NOT NULL, 
		ritmo text NOT NULL, 
		ivs text NOT NULL, 
	 	compañerosSexuales int NOT NULL, 
	  	gestaciones int NOT NULL, 
		partos int NOT NULL, 
		abortos int NOT NULL, 
		ceareas int NOT NULL, 
		ectopico text NOT NULL, 
		molar text NOT NULL, 
		fup text NOT NULL, 
		fum text NOT NULL, 
		fpp text NOT NULL, 
		padiecimientoActual text NOT NULL,
  		idHistoriaClinica int NOT NULL,
  		PRIMARY KEY (idAantecedentesGinecoObstetricos)
);
//FK de "ANTECEDENTES GINECO OBSTETRICOS" a "HISTORIAS CLINICAS"
ALTER TABLE antecedentesGinecoObstetricos
ADD FOREIGN KEY (idHistoriaClinica) REFERENCES historiasClinicas(idHistoriaClinica);
//View pacientes-domicilios
CREATE VIEW paciente_domicilio AS
    SELECT 
		pacientes.nombres, pacientes.apellidoPaterno, pacientes.apellidoMaterno, 
		pacientes.edad, pacientes.estadocivil, pacientes.ocupacion, pacientes.escolaridad,
		pacientes.ciudaddenacimiento, pacientes.entidaddenacimiento, pacientes.telefono, 
		pacientes.correo, pacientes.idginecologa, domicilios.*
    FROM 
		pacientes, 
		domicilios
    WHERE pacientes.idPaciente=domicilios.idPaciente;
//Historia clinica (VISTA)
CREATE VIEW 
	vw_historias_clinicas AS
SELECT 
	pac.idPaciente, pac.nombres, pac.apellidoPaterno, pac.apellidoMaterno, pac.edad, pac.estadoCivil, pac.ocupacion,
	CONCAT  (pac.ciudadDeNacimiento, ', ', pac.entidadDeNacimiento) AS LugarDeNacimiento, pac.fechanacimiento,
	pac.escolaridad, pac.telefono, pac.correo, 
	dom.municipio, dom.colonia, dom.calle, dom.numero, dom.cp,
	hc.fecha as fechaHC, hc.hora as horaHC,
	ahf.diabetes as diabeteshf, ahf.hta as htahf, ahf.neoplasticos as neoplasticoshf, ahf.cardiopatias as cardiopatiashf,
	ahf.tiroides as tiroideshf, ahf.especificaciones as especificacioneshf,
	app.diabetes as diabetespp, app.hta as htapp, app.nefropatias as nefropatiaspp, app.cardiopatias as cardiopatiaspp,
	app.epilepsia as epilepsiapp,
	apnp.tabaquismo, apnp.alcoholismo, apnp.drogas, apnp.rubeola, apnp.influenza, apnp.tetanos, apnp.covid19,
	ago.*
FROM
	pacientes as pac, domicilios as dom, historiasClinicas as hc, antecedentesHeredoFamiliares as ahf,
	antecedentesPersonalesPatologicos as app, antecedentesPersonalesNoPatologicos as apnp, 
	antecedentesGinecoObstetricos as ago
WHERE
	pac.idPaciente=dom.idPaciente and pac.idPaciente=hc.idPaciente and 
	hc.idHistoriaClinica=ahf.idHistoriaClinica and hc.idHistoriaClinica=app.idHistoriaClinica and
	hc.idHistoriaClinica=apnp.idHistoriaClinica and hc.idHistoriaClinica=ago.idHistoriaClinica
ORDER BY 
	pac.apellidoPaterno, pac.apellidoMaterno, pac.nombres asc;
