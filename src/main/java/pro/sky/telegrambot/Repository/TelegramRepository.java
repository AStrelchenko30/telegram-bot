package pro.sky.telegrambot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.model.NotificationTask;

import java.util.List;


public interface TelegramRepository extends JpaRepository<NotificationTask, Long> {
}
