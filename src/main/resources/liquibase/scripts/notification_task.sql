-- liquibase formatted sql

-- changeset anya:1

CREATE TABLE notification_task
(
    id SERIAL primary key not null,
        chatId BIGINT not null,
        msg TEXT not null ,
    time_massage timestamp not null

)

