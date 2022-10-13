create table users(
                      id serial primary key,
                      email varchar(100),
                      username varchar(100),
                      user_password varchar(20),
                      created timestamp,
                      updated timestamp,
                      created_by varchar(100),
                      updated_by varchar(100)
);