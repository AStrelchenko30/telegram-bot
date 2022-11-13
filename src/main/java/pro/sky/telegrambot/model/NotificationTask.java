package pro.sky.telegrambot.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
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
    @GeneratedValue
    private Long chatId;

    private String msg;

    private LocalDateTime time_massage;

    public NotificationTask(Long chatId, String msg, LocalDateTime time_massage) {
        this.chatId = chatId;
        this.msg = msg;
        this.time_massage = time_massage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        NotificationTask that = (NotificationTask) o;
        return chatId != null && Objects.equals(chatId, that.chatId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
