create table user_transaction(
                                 id serial primary key,
                                 userId int8,
                                 accountId int8,
                                 transactionType varchar(50),
                                 amount int8,
                                 created timestamp,
                                 updated timestamp,
                                 created_by varchar(100),
                                 updated_by varchar(100)
);