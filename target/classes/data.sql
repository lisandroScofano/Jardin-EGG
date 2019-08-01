//Creo Salitas
INSERT INTO  `golden_kids`.`salita` (`id` ,`hora_entrada` ,`hora_salida` ,`nombre`) VALUES ('068220e5-64b8-4021-8c5d-47ae00bb2909',  '09:00:00',  '12:30:00',  'Verde');
INSERT INTO  `golden_kids`.`salita` (`id` ,`hora_entrada` ,`hora_salida` ,`nombre`) VALUES ('b8ce5bcf-dd17-41d3-b781-77cf14c5eeeb',  '08:00:00',  '12:00:00',  'Roja');
INSERT INTO  `golden_kids`.`salita` (`id` ,`hora_entrada` ,`hora_salida` ,`nombre`) VALUES ('477c0427-dc21-4428-8354-24197ac9bb8d',  '09:15:00',  '12:56:00',  'Azul');
INSERT INTO  `golden_kids`.`salita` (`id` ,`hora_entrada` ,`hora_salida` ,`nombre`) VALUES ('412b3147-d192-40c0-af2f-7259b9b66a8f',  '09:45:00',  '14:30:00',  'Amarilla');

//Creo Autorizados
INSERT INTO  `golden_kids`.`autorizados`(`dni` ,`apellido` ,`nombre` ,`parentesco`,`telefono1`,`telefono2`) VALUES ('42167741','Ponce Guerra','Joaquin','PADRE','2612066707','');
INSERT INTO  `golden_kids`.`autorizados`(`dni` ,`apellido` ,`nombre` ,`parentesco`,`telefono1`,`telefono2`) VALUES ('36787128','Oviedo','Martin','PADRE','2619826732','');
INSERT INTO  `golden_kids`.`autorizados`(`dni` ,`apellido` ,`nombre` ,`parentesco`,`telefono1`,`telefono2`) VALUES ('18662194','Marcela','Guerra','TIA','2612066707','');


//Creo Alumnos
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577240',  'GOMEZ',  '2010-07-01',  'LAURA',  '068220e5-64b8-4021-8c5d-47ae00bb2909');
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577241',  'RETA',  '2010-07-01',  'GUILLERMINA',  '068220e5-64b8-4021-8c5d-47ae00bb2909');
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577242',  'CASALE',  '2010-07-01',  'FERNANDA',  'b8ce5bcf-dd17-41d3-b781-77cf14c5eeeb');
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577243',  'CIVIT',  '2010-07-01',  'ROMINA',  '477c0427-dc21-4428-8354-24197ac9bb8d');
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577244',  'GARCIA',  '2010-07-01',  'MARTA',  '412b3147-d192-40c0-af2f-7259b9b66a8f');


//Creo Roles
INSERT INTO  `golden_kids`.`rol` (`id`,`perfil`) VALUES ('246fa3cd-ac90-4844-b808-a8a8c75c0d7c',  '0');
INSERT INTO  `golden_kids`.`rol` (`id`,`perfil`) VALUES ('bc052b6e-cecb-4273-b2e1-4e7616162a24',  '1');
INSERT INTO  `golden_kids`.`rol` (`id`,`perfil`) VALUES ('dc87eccd-be25-4042-aa5c-24de571a6fc2',  '2');

//Creo Usuarios
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290075',  'SCOFANO',  '2019-07-31', NULL ,  '123@456.com',  'LISANDRO',  'lisandro',  '1234',  '4457898',  'PADRE');
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290076',  'MUNOZ',  '2019-07-31', NULL ,  '456@456.com',  'MARCELO',  'marcelo',  '1234',  '4457899',  'DIRECTIVO');
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290077',  'RAMIREZ',  '2019-07-31', NULL ,  '789@456.com',  'RAMIRO',  'ramiro',  '1234',  '4457891',  'DOCENTE');
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290078',  'FERNANDEZ',  '2019-07-31', NULL ,  '321@456.com',  'CARLOS',  'carlos',  '1234',  '4457892',  'DOCENTE');
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290079',  'RODRIGUEZ',  '2019-07-31', NULL ,  '567@456.com',  'PEDRO',  'pedro',  '1234',  '4457893',  'DOCENTE');


//Creo Docentes
INSERT INTO  `golden_kids`.`docente` (`id` ,`tipo_docente` ,`salita_id` ,`usuario_dni`) VALUES ('f07f130c-159a-4d5e-ab10-ae42931dd065 ',  'AUXILIAR',  '068220e5-64b8-4021-8c5d-47ae00bb2909', '32290077');
INSERT INTO  `golden_kids`.`docente` (`id` ,`tipo_docente` ,`salita_id` ,`usuario_dni`) VALUES ('f07f130c-159a-4d5e-ab10-ae42931dd065 ',  'TITULAR',  'b8ce5bcf-dd17-41d3-b781-77cf14c5eeeb', '32290078');
INSERT INTO  `golden_kids`.`docente` (`id` ,`tipo_docente` ,`salita_id` ,`usuario_dni`) VALUES ('f07f130c-159a-4d5e-ab10-ae42931dd065 ',  'REEMPLAZANTE',  '477c0427-dc21-4428-8354-24197ac9bb8d', '32290079');


//Inscripcion
INSERT INTO  `golden_kids`.`inscripcion` (`id`,`fecha_alta`,`fecha_baja`,`alumno_dni`,`salita_id`) VALUES ('893b2a83-d8aa-4836-a0b2-0883fd14f863','2019-07-31','null','3577241','068220e5-64b8-4021-8c5d-47ae00bb2909');
INSERT INTO  `golden_kids`.`inscripcion` (`id`,`fecha_alta`,`fecha_baja`,`alumno_dni`,`salita_id`) VALUES ('945b70f5-cacb-4abc-a278-84707e4d1b6b','2019-07-31','null','3577243','b8ce5bcf-dd17-41d3-b781-77cf14c5eeeb');
INSERT INTO  `golden_kids`.`inscripcion` (`id`,`fecha_alta`,`fecha_baja`,`alumno_dni`,`salita_id`) VALUES ('6d648df5-aa7d-4601-acfe-725d77ae0f05','2019-07-31','null','3577244','412b3147-d192-40c0-af2f-7259b9b66a8f');









