select *from jogo;
select *from arbitro;
select *from categoria;
select *from equipe_composta;
select *from equipe_simples;
select *from pontos;
select *from resultado;

delete from jogo;
delete from arbitro;
delete from categoria;
delete from equipe_composta;
delete from equipe_simples;
delete from pontos;
delete from resultado;

create table jogo(
	time1 varchar(30),
	time2 varchar(30),
	modalidade varchar(20),
	nomeArbitro varchar(20)
)

create table arbitro(
	nomeArbitro varchar(30),
	identidade int,
	qtd_partidas int,
	primary key(nomeArbitro)
)

create table CATEGORIA(
	mirim varchar,
	juvenil varchar,
	nome varchar
)

create table EQUIPE_COMPOSTA(
	time1 varchar(20),
	jogador1 varchar(30),
	nome1 varchar(30),
	tecnico1 varchar(20),
	
	time2 varchar(20),
	jogador2 varchar(30),
	nome2 varchar(30),
	tecnico2 varchar(30)
)

create table EQUIPE_SIMPLES(
	jogador1 varchar(30),
	dataNasc1 int,
	jogador2 varchar(30),
	dataNasc2 int,
	tecnico1 varchar(30),
	tecnico2 varchar(30)
)

create table pontos(
	time1 varchar(30),
	qtd_P1 int,
	set1 int,
	
	time2 varchar(30),
	qtd_P2 int,
	set2 int
)

create table RESULTADO(--fazer o calculo da quantidade de sets para encontrar o vencedor
	vencedor varchar(30),
	set_vencedor int,
	
	perdedor varchar(30),
	set_perdedor int,
	ano int,
	primary key (vencedor)
)

-- Database: BDTenisMesa

-- DROP DATABASE "BDTenisMesa";

CREATE DATABASE "BDatvTenisMesa"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;