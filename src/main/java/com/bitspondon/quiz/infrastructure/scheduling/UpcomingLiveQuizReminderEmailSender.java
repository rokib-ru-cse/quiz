package com.bitspondon.quiz.infrastructure.scheduling;

import com.bitspondon.quiz.application.repository.ILiveQuizRepository;
import com.bitspondon.quiz.application.service.IEmailService;
import com.bitspondon.quiz.domain.entities.LiveQuiz;
import com.bitspondon.quiz.domain.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UpcomingLiveQuizReminderEmailSender {


    private final ILiveQuizRepository liveQuizRepository;
    private final IEmailService emailService;

    @Scheduled(fixedRate = 1800000) // Run every 30 minutes
    public void sendReminderEmails() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime thirtyMinutesLater = now.plusMinutes(30);
        List<LiveQuiz> liveQuizzesWithInThirtyMinutes = liveQuizRepository.findByStartTimeBetween(now, thirtyMinutesLater);

        for (LiveQuiz liveQuiz : liveQuizzesWithInThirtyMinutes) {
            Set<User> userSet = liveQuiz.getUsers();

            String subject = "Upcoming Live Quiz Reminder";
            User user = userSet.stream().findFirst().get();
            String content = "Dear " + user.getFirstname() + " " + user.getLastname() + ",\n\n" +
                    "Just a reminder that the upcoming live quiz will start in 30 minutes.\n" +
                    "Make sure you're ready to participate!\n\n" + "Best regards,\nYour Quiz Team";

            emailService.sendHtmlEmail("rokibahmed.ru.cse@gmail.com", subject, content);

        }
    }

}
