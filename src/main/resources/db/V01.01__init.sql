create table user (id                   numeric      not null auto_increment,
                   created_date         timestamp    null default current_timestamp,
                   updated_date         timestamp    null,
                   username             varchar(255) not null,
                   password             varchar(255) not null,
                   email                varchar(255) not null,
                   status               varchar(32)  not null,
                   last_login           timestamp    null,
                   last_password_change timestamp    null,
                   auth_key             varchar(255) null,
                   verification_key     varchar(255) null,
   primary key (id)
);

insert into user (username, password, email, status)
   values ('admin', 'Janeka1974', 'ondrej.linek@gmail.com', 'open');
