package pro.sky.telegrambot.Repository;

import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.model.NotificationTask;

import java.util.List;


public interface TelegramRepository extends JpaRepository<NotificationTask, Long> {
    @Query(value = "SELECT * from notification_task where time_message notnull ",nativeQuery = true)
    void run(Long id);


}
