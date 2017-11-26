drop schema if exists matchmaker cascade;
create schema matchmaker;

create table matchmaker.game_status (
    id int4 primary key,
    name varchar(20) unique not null
);

insert into matchmaker.game_status values (1, 'awaiting'), (2, 'launched'), (3, 'finished');

create table matchmaker.player_status (
    id int4 primary key,
    name varchar(20) unique not null
);

insert into matchmaker.player_status values (1, 'onine'), (2, 'offline');

create table matchmaker.game (
    id int8 primary key,
    status int4 references matchmaker.game_status (id)
);

create table matchmaker.player (
    id serial primary key,
    login varchar(30) unique not null,
    password varchar(20) not null,
    wins int4 not null,
    status int4 references matchmaker.player_status (id)
);

create table matchmaker.match (
    game_id int8 references matchmaker.game (id),
    player_id int4 references matchmaker.player (id),
    primary key (game_id, player_id)
);

commit;