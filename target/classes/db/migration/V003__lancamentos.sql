CREATE TABLE lancamento (
	id bigserial,
	categoria_id bigint,
	pessoa_id bigint,
	descricao varchar(50) not null,
	data_vencimento date not null,
	data_pagamento date,
	valor numeric (15,2),
	observacao varchar(100),
	tipo varchar(20) not null,
	CONSTRAINT pk_lancamento PRIMARY KEY (id),
	CONSTRAINT fk_categoria FOREIGN KEY (categoria_id) REFERENCES categoria (id),
	CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_id) REFERENCES pessoa (id)
);