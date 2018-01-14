CREATE TABLE pessoa (
	id bigserial,
	nome VARCHAR(150) NOT NULL,
	ativo boolean,
	logradouro VARCHAR(30),
	numero VARCHAR(30),
	complemento VARCHAR(30),
	bairro VARCHAR(30),
	cep VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30),
	CONSTRAINT pk_pessoa PRIMARY KEY (id)
);