-- liquibase formatted sql

-- changeset anya:1

CREATE TABLE notification_task
(

    chat_Id      BIGINT primary key not null,
    msg         TEXT               not null,
    time_Message timestamptz          not null

)

