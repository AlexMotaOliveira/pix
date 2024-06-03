create table tb_pix
(
    numero_agencia                integer     not null,
    tipo_chave                    tinyint     not null check (tipo_chave between 0 and 4),
    tipo_conta                    tinyint     not null check (tipo_conta between 0 and 1),
    data_hora_inativacao_da_chave timestamp(6),
    data_hora_inclusao_da_chave   timestamp(6),
    numero_conta                  bigint      not null,
    id                            uuid        not null,
    nome_correntista              varchar(30) not null,
    sobrenome_correntista         varchar(45),
    valor_chave                   varchar(77) not null,
    primary key (id)
)
