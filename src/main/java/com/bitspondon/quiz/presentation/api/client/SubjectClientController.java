package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.entities.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ClientUrl.SUBJECT)
public class SubjectClientController {

    private final ISubjectUseCase subjectUseCase;

    public SubjectClientController(ISubjectUseCase subjectUseCase) {
        this.subjectUseCase = subjectUseCase;
    }

    @GetMapping
    public ReturnReponse<Subject> getSubjects() {
        List<Subject> subjectList = subjectUseCase.getSubjects();
        return ReturnReponse.<Subject>builder().message("data found successfully").succeeded(true).values(subjectList).build();
    }

}