create table user (
    id bigint not null auto_increment,
    username varchar (255) not null,
    password varchar (255) not null,
    email varchar (255) not null,
    status varchar (32) not null,
    last_login datetime null,
    last_password_change datetime null,
    auth_key varchar (255) null,
    verification_key (255) null,
    primary key (id)
);

insert into user (username, password, email, status)
values ('admin', 'Janeka1974', 'ondrej.linek@gmail.com', 'open');
