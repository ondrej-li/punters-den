create table league (
   id           numeric      not null auto_increment,
   created_date timestamp    null default current_timestamp,
   updated_date timestamp    null,
   name         varchar(255) not null,
   description  varchar(255) not null,
   country_id   numeric      not null,
   user_created char(1)      not null default 'N',
   active       char(1)      not null default 'Y',
   created_by   numeric      null,
   primary key (id)
);

create table country (
   id           numeric      not null auto_increment,
   created_date timestamp    null default current_timestamp,
   updated_date timestamp    null,
   name         varchar(255) not null,
   description  varchar(255) not null,
   primary key (id)
);

create table team (
   id           numeric      not null auto_increment,
   created_date timestamp    null default current_timestamp,
   updated_date timestamp    null,
   name         varchar(255) not null,
   description  varchar(255) not null,
   country_id   numeric      not null,
   primary key (id)
);

create table match (
   id                numeric       not null auto_increment,
   created_date      timestamp     null default current_timestamp,
   updated_date      timestamp     null,
   fixture_id        numeric       not null,
   league_id         numeric       not null,
   home_team_id      numeric       not null,
   away_team_id      numeric       not null,
   home_goals        numeric(3, 0) not null,
   away_goals        numeric(3, 0) not null,
   start_time        timestamp     null,
   end_time          timestamp     null,
   home_yellow_cards numeric(2),
   away_yellow_cards numeric(2),
   home_red_cards    numeric(1),
   away_red_cards    numeric(1),
   created_by        numeric       null,
   primary key (id)
);
create table standing (
   id            numeric    not null auto_increment,
   created_date  timestamp  null default current_timestamp,
   updated_date  timestamp  null,
   league_id     numeric    not null,
   team_id       numeric    not null,
   points        numeric(5) not null,
   wins          numeric(5) not null,
   losses        numeric(5) not null,
   draws         numeric(5) not null,
   goals_given   numeric(6) not null,
   goal_received numeric(6) not null,
   primary key (id)

)