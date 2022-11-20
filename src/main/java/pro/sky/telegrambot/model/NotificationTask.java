package pro.sky.telegrambot.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity(name = "notification_task")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class NotificationTask {
    @Id
    private Long id;
    @GeneratedValue

    private Long chatId;

    private String msg;

    private LocalDateTime timeMessage;

    public NotificationTask(Long chatId, String msg, LocalDateTime timeMessage) {
        this.chatId = chatId;
        this.msg = msg;
        this.timeMessage = timeMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return Objects.equals(id, that.id) && Objects.equals(chatId, that.chatId) && Objects.equals(msg, that.msg) && Objects.equals(timeMessage, that.timeMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, msg, timeMessage);
    }
}
