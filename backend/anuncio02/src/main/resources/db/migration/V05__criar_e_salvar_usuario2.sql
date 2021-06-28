CREATE TABLE public.tb_usuario (
	id SERIAL PRIMARY KEY,
	name varchar (100) NOT NULL,
	email varchar (50) NOT NULL,
	password varchar (256) NOT NULL,
	profile integer NOT NULL, 
	active boolean NOT NULL,
	date_register Date NOT NULL
);

INSERT INTO tb_usuario 
(name, email, password, profile, active, date_register)
VALUES
( 'Rogerio Reis ADS4','rogerio@gmail.com', 'admin', '0','true', '2021-05-13'),
( 'Gabriel Cunha ADS4','gabriel@gmail.com', 'admin', '0','true', '2021-05-13'),
( 'Aires Ribeiro ADS4','aires@gmail.com', 'admin','0','true', '2021-05-13'),
( 'Lucas França ADS4','lucas@gmail.com', 'admin','0','true', '2021-05-13');


