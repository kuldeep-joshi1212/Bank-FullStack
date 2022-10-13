create table user_account(
                             id serial primary key,
                             userId int8,
                             account_balance int8,
                             created timestamp,
                             updated timestamp,
                             created_by varchar(100),
                             updated_by varchar(100)
);