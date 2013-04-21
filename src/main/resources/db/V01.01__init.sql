create table user (
    id primary key,
    username varchar (255),
    password varchar (255),
    email varchar (255),
    status (varchar 32),
    last_login (datetime),
    last_password_change (datetime)
)