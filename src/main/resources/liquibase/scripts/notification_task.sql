-- liquibase formatted sql

-- changeset anya:1

CREATE TABLE notification_task
(
    id           BIGSERIAL PRIMARY KEY,
    chat_Id      BIGINT     not null,
    msg          TEXT       not null,
    time_Message timestamp not null

)

