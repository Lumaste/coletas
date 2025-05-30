CREATE SEQUENCE SEQ_COOPERATIVA
  START WITH 1
  INCREMENT BY 1
  NOCACHE
  NOCYCLE;

CREATE TABLE TBL_COOPERATIVAS (
    ID_COOPERATIVA INT PRIMARY KEY,
    NOME VARCHAR(100),
    ENDERECO VARCHAR(200),
    TELEFONE VARCHAR(20),
    EMAIL VARCHAR(100),
    DATA_CRIACAO DATE DEFAULT SYSDATE NOT NULL,
    HORA_CRIACAO TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);