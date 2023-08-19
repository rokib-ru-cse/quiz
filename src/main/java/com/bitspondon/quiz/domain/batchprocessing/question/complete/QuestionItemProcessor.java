//package com.bitspondon.quiz.domain.batchprocessing.question.complete;
//
//import com.bitspondon.quiz.application.repository.ILevelRepository;
//import com.bitspondon.quiz.application.repository.ISubjectRepository;
//import com.bitspondon.quiz.domain.entities.Level;
//import com.bitspondon.quiz.domain.entities.Question;
//import com.bitspondon.quiz.domain.entities.Subject;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//@Component
//public class QuestionItemProcessor implements ItemProcessor<QuestionDTO, Question> {
//
//    @Autowired
//    private ILevelRepository levelRepository;
//
//    @Autowired
//    private ISubjectRepository subjectRepository;
//
//    @Override
//    public Question process(QuestionDTO questionDTO) {
//        Question question = new Question();
//        question.setTitle(questionDTO.getTitle());
//        question.setDescription(questionDTO.getDescription());
//
//        Level level = levelRepository.findById(questionDTO.getLevelId()).orElse(null);
//        question.setLevel(level);
//
//        Subject subject = subjectRepository.findById(questionDTO.getSubjectId()).orElse(null);
//        question.setSubject(subject);
//
//        // Set other properties as needed
//
//        return question;
//    }
//}
//
