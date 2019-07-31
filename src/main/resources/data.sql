//Creo Alumnos
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577251',  'GOMEZ',  '2010-07-01',  'LAURA',  '068220e5-64b8-4021-8c5d-47ae00bb2909')
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577251',  'RETA',  '2010-07-01',  'GUILLERMINA',  '068220e5-64b8-4021-8c5d-47ae00bb2909')
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577251',  'CASALE',  '2010-07-01',  'FERNANDA',  'b8ce5bcf-dd17-41d3-b781-77cf14c5eeeb')
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577251',  'CIVIT',  '2010-07-01',  'ROMINA',  '477c0427-dc21-4428-8354-24197ac9bb8d')
INSERT INTO  `golden_kids`.`alumno` (`dni` ,`apellido` ,`fecha_nacimiento` ,`nombre` ,`salita_id`) VALUES ('3577251',  'GARCIA',  '2010-07-01',  'MARTA',  '412b3147-d192-40c0-af2f-7259b9b66a8f')



//Creo Alumnos_contacto

//Creo Autorizados

//Creo Autorizados_Alumno

//Creo Docentes
INSERT INTO  `golden_kids`.`docente` (`id` ,`tipo_docente` ,`salita_id` ,`usuario_dni`) VALUES ('f07f130c-159a-4d5e-ab10-ae42931dd065 ',  'AUXILIAR',  '068220e5-64b8-4021-8c5d-47ae00bb2909', '32290077')
INSERT INTO  `golden_kids`.`docente` (`id` ,`tipo_docente` ,`salita_id` ,`usuario_dni`) VALUES ('f07f130c-159a-4d5e-ab10-ae42931dd065 ',  'TITULAR',  'b8ce5bcf-dd17-41d3-b781-77cf14c5eeeb', '32290078')
INSERT INTO  `golden_kids`.`docente` (`id` ,`tipo_docente` ,`salita_id` ,`usuario_dni`) VALUES ('f07f130c-159a-4d5e-ab10-ae42931dd065 ',  'REEMPLAZANTE',  '477c0427-dc21-4428-8354-24197ac9bb8d', '32290079')

//Inscripcion

//Creo Roles
INSERT INTO  `golden_kids`.`rol` (`id`,`rol`) VALUES ('246fa3cd-ac90-4844-b808-a8a8c75c0d7c',  'DIRECTIVO')
INSERT INTO  `golden_kids`.`rol` (`id`,`rol`) VALUES ('bc052b6e-cecb-4273-b2e1-4e7616162a24',  'DOCENTE')
INSERT INTO  `golden_kids`.`rol` (`id`,`rol`) VALUES ('dc87eccd-be25-4042-aa5c-24de571a6fc2',  'PADRE')

//Creo Salitas
INSERT INTO  `golden_kids`.`salita` (`id` ,`hora_entrada` ,`hora_salida` ,`nombre`) VALUES ('068220e5-64b8-4021-8c5d-47ae00bb2909',  '09:00:00',  '12:30:00',  'Verde')
INSERT INTO  `golden_kids`.`salita` (`id` ,`hora_entrada` ,`hora_salida` ,`nombre`) VALUES ('b8ce5bcf-dd17-41d3-b781-77cf14c5eeeb',  '08:00:00',  '12:00:00',  'Roja')
INSERT INTO  `golden_kids`.`salita` (`id` ,`hora_entrada` ,`hora_salida` ,`nombre`) VALUES ('477c0427-dc21-4428-8354-24197ac9bb8d',  '09:15:00',  '12:56:00',  'Azul')
INSERT INTO  `golden_kids`.`salita` (`id` ,`hora_entrada` ,`hora_salida` ,`nombre`) VALUES ('412b3147-d192-40c0-af2f-7259b9b66a8f',  '09:45:00',  '14:30:00',  'Amarilla')

//Creo Usuarios
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290075',  'SCOFANO',  '2019-07-31', NULL ,  '123@456.com',  'LISANDRO',  'lisandro_scofano',  '1234',  '4457898',  'PADRE')
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290076',  'MUNOZ',  '2019-07-31', NULL ,  '456@456.com',  'MARCELO',  'marcelo_munoz',  '1234',  '4457899',  'DIRECTIVO')
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290077',  'RAMIREZ',  '2019-07-31', NULL ,  '789@456.com',  'RAMIRO',  'ramiro_ramirez',  '1234',  '4457891',  'DOCENTE')
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290078',  'FERNANDEZ',  '2019-07-31', NULL ,  '321@456.com',  'CARLOS',  'carlos_fernandez',  '1234',  '4457892',  'DOCENTE')
INSERT INTO  `golden_kids`.`usuario` (`dni` ,`apellido` ,`fecha_alta` ,`fecha_baja` ,`mail` ,`nombre` ,`nombre_usuario` ,`password` ,`telefono` ,`tipo_perfil`) VALUES ('32290079',  'RODRIGUEZ',  '2019-07-31', NULL ,  '567@456.com',  'PEDRO',  'pedro_rodriguez',  '1234',  '4457893',  'DOCENTE')

//Creo Usuario_roles
INSERT INTO  `golden_kids`.`usuario_roles` (`usuario_dni` ,`roles_id`) VALUES ('32290075',  'dc87eccd-be25-4042-aa5c-24de571a6fc2')
INSERT INTO  `golden_kids`.`usuario_roles` (`usuario_dni` ,`roles_id`) VALUES ('32290076',  '246fa3cd-ac90-4844-b808-a8a8c75c0d7c')
INSERT INTO  `golden_kids`.`usuario_roles` (`usuario_dni` ,`roles_id`) VALUES ('32290077',  'bc052b6e-cecb-4273-b2e1-4e7616162a24')
INSERT INTO  `golden_kids`.`usuario_roles` (`usuario_dni` ,`roles_id`) VALUES ('32290078',  'bc052b6e-cecb-4273-b2e1-4e7616162a24')
INSERT INTO  `golden_kids`.`usuario_roles` (`usuario_dni` ,`roles_id`) VALUES ('32290079',  'bc052b6e-cecb-4273-b2e1-4e7616162a24')




