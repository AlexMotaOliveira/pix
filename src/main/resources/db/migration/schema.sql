DROP DATABASE IF Exists `pix`;
CREATE SCHEMA `pix` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin ;
use pix;

# TODO add index a tabelas para consultas de campos não indexados
create table tb_cadastro_chaves_pix (
                                        id binary(16) not null,
                                        id_chave_pix binary(16),
                                        id_correntista bigint,
                                        primary key (id)
) engine=MyISAM;

create table tb_chave_pix (
                              id binary(16) not null,
                              data_hora_inativacao_da_chave datetime(6),
                              data_hora_inclusao_da_chave datetime(6) not null,
                              situacao_chave tinyint not null check (situacao_chave between 0 and 1),
                              tipo_chave tinyint not null check (tipo_chave between 0 and 4),
                              valor_chave varchar(77) not null,
                              primary key (id)
) engine=MyISAM;

create table tb_correntista (
                                id_correntista bigint not null auto_increment,
                                nome_correntista varchar(30) not null,
                                numero_agencia integer not null check ((numero_agencia<=9999) and (numero_agencia>=1)),
                                numero_conta bigint not null check ((numero_conta<=99999999) and (numero_conta>=1)),
                                sobrenome_correntista varchar(45),
                                tipo_conta tinyint not null check (tipo_conta between 0 and 1),
                                tipo_cliente tinyint not null check (tipo_cliente between 0 and 1),
                                primary key (id_correntista)
) engine=MyISAM;
