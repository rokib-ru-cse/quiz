package com.bitspondon.quiz.infrastructure.scheduling;

import com.bitspondon.quiz.application.repository.ILiveQuizRepository;
import com.bitspondon.quiz.domain.entities.LiveQuiz;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


@Service
@AllArgsConstructor
public class LiveQuizToOldQuiz {

    private final ILiveQuizRepository liveQuizRepository;

    @Scheduled(cron = "0 0 22 * * *", zone = "UTC") //BDT = everyday night 4 am. this will be executed
    public void runJob() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        List<LiveQuiz> endedQuizzes = liveQuizRepository.findEndedQuizzes(currentDate, currentTime);

        System.out.println("Job executed at 4 AM.");
    }
}
