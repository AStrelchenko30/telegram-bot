package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.model.NotificationTask;
import pro.sky.telegrambot.Repository.TelegramRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {
    public TelegramRepository telegramRepository;
    @Autowired
    private final TelegramBot telegramBot;

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);

    public TelegramBotUpdatesListener(TelegramRepository telegramRepository, TelegramBot telegramBot) {
        this.telegramRepository = telegramRepository;
        this.telegramBot = telegramBot;
    }

    Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }


    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            Message message = update.message();
            Long chatId = update.message().chat().id();
            String textM = message.text();


            if ("/start".equals(textM)) {
                SendMessage sendMessage = new SendMessage(chatId, "Hi,how are you?");
                telegramBot.execute(sendMessage);

            }

            Matcher matcher = pattern.matcher("01.01.2022 20:00 Сделать домашнюю работу");

            if (matcher.matches()) {
                String date = matcher.group(1);
                String msg = matcher.group(3);
                LocalDateTime timeMessage = LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                NotificationTask notificationTask = new NotificationTask(chatId, msg, timeMessage);
                telegramRepository.save(notificationTask);

            }
        });
        run();
        return UpdatesListener.CONFIRMED_UPDATES_ALL;


    }


    @Scheduled(cron = "0 0/1 * * * *")
    public void run() {
        List<NotificationTask> messageTime = telegramRepository.findByTimeMessage(LocalDateTime.now());
        for (NotificationTask notificationTask : messageTime) {
            SendMessage sendMessage = new SendMessage(notificationTask.getChatId(), notificationTask.getMsg());
            telegramBot.execute(sendMessage);
        }
    }
}





