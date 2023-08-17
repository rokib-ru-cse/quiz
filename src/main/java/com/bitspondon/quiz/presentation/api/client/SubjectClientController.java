package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.entities.Subject;
import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/client/subject")
public class SubjectClientController {

    @Autowired
    private ISubjectUseCase subjectUseCase;

    @GetMapping
    public ReturnReponse<Subject> getSubjects() {
//        return subjectUseCase.getSubjects();
        return null;
    }

}