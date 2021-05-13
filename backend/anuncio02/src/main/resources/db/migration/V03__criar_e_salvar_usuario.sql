CREATE TABLE public.tb_usuario (
	id SERIAL PRIMARY KEY,
	nome varchar (100) NOT NULL,
	email varchar (50) NOT NULL,
	senha varchar (6) NOT NULL,
	telefone varchar (12) NOT NULL,
	perfil integer NOT NULL, 
	status integer NOT NULL,
	dt_cadastro Date NOT NULL
);

INSERT INTO tb_usuario 
(nome, email, senha, telefone, perfil, status, dt_cadastro)
VALUES
( 'Rogerio Reis ADS4','rogerio@gmail.com', 'admin','62985914444', '0','0', '2021-05-13'),
( 'Gabriel Cunha ADS4','gabriel@gmail.com', 'admin','62985914444', '0','0', '2021-05-13'),
( 'Aires Ribeiro ADS4','aires@gmail.com', 'admin','62985916666','0','0', '2021-05-13'),
( 'Lucas França ADS4','lucas@gmail.com', 'admin','62985917777','0','0', '2021-05-13');


