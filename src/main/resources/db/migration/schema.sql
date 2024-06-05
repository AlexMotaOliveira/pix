DROP TABLE IF EXISTS  nao_usar, tb_cadastro_chaves_pix, tb_correntista, tb_pix;

CREATE TABLE nao_usar
(
    id                            VARCHAR(77)  NOT NULL,
    tipo_chave                    SMALLINT    NOT NULL,
    valor_chave                   VARCHAR(77) NOT NULL,
    tipo_conta                    SMALLINT    NOT NULL,
    numero_agencia                INT         NOT NULL,
    numero_conta                  BIGINT      NOT NULL,
    nome_correntista              VARCHAR(30) NOT NULL,
    sobrenome_correntista         VARCHAR(45) NULL,
    identificacao_cliente         VARCHAR(14) NOT NULL,
    situacao_chave                SMALLINT    NOT NULL,
    data_hora_inclusao_da_chave   datetime    NOT NULL,
    data_hora_inativacao_da_chave datetime    NULL,
    tipo_cliente                  SMALLINT    NOT NULL,
    CONSTRAINT pk_nao_usar PRIMARY KEY (id)
);

CREATE TABLE tb_cadastro_chaves_pix
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    correntista_id VARCHAR(255)          NULL,
    chave_pix_id   VARCHAR(77)            NULL,
    CONSTRAINT pk_tb_cadastro_chaves_pix PRIMARY KEY (id)
);

CREATE TABLE tb_correntista
(
    id_correntista        VARCHAR(255) NOT NULL,
    tipo_conta            SMALLINT     NOT NULL,
    numero_agencia        INT          NOT NULL,
    numero_conta          BIGINT       NOT NULL,
    nome_correntista      VARCHAR(30)  NOT NULL,
    sobrenome_correntista VARCHAR(45)  NULL,
    tipo_cliente          SMALLINT     NOT NULL,
    CONSTRAINT pk_tb_correntista PRIMARY KEY (id_correntista)
);

CREATE TABLE tb_pix
(
    id                            VARCHAR(77)  NOT NULL,
    tipo_chave                    SMALLINT    NOT NULL,
    valor_chave                   VARCHAR(77) NOT NULL,
    situacao_chave                SMALLINT    NOT NULL,
    data_hora_inclusao_da_chave   datetime    NOT NULL,
    data_hora_inativacao_da_chave datetime    NULL,
    CONSTRAINT pk_tb_pix PRIMARY KEY (id)
);

ALTER TABLE tb_cadastro_chaves_pix
    ADD CONSTRAINT FK_TB_CADASTRO_CHAVES_PIX_ON_CHAVE_PIX FOREIGN KEY (chave_pix_id) REFERENCES tb_pix (id);

ALTER TABLE tb_cadastro_chaves_pix
    ADD CONSTRAINT FK_TB_CADASTRO_CHAVES_PIX_ON_CORRENTISTA FOREIGN KEY (correntista_id) REFERENCES tb_correntista (id_correntista);
