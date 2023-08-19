package com.bitspondon.quiz.domain.batchprocessing.question;

import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.batch.item.ItemProcessor;

public class QuestionProcessor implements ItemProcessor<Question, Question> {
    @Override
    public Question process(Question question) throws Exception {
        return question;
//        if(question.getTitle().equals("")) {
//             return null;
//        }else{
//
//        }
    }
}
