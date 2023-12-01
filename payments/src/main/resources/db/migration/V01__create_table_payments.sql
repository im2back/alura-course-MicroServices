create table payments(
	id BIGINT  NOT NULL AUTO_INCREMENT,
    amount decimal(10,2) NOT NULL ,
    name_column VARCHAR(100) NOT NULL,
    number_column VARCHAR(19) NOT NULL,
    expiration  VARCHAR(9)  NOT NULL,
    code_column VARCHAR(3) not null,
    status_column  VARCHAR(255) NOT NULL,
    pedido_id VARCHAR(20) NOT NULL,
    forma_de_pagamento_id VARCHAR(20) NOT NULL,

	PRIMARY KEY(id)
);