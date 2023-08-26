package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.ClientUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.Subject;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SubjectClientController {

    private final ISubjectUseCase subjectUseCase;

    @GetMapping(ClientUrl.SUBJECT)
    public ReturnReponse<List<Subject>> getSubjects() {
        List<Subject> subjectList = subjectUseCase.getSubjects();
        return ReturnReponse.<List<Subject>>builder().message("data found successfully").succeeded(true).value(subjectList).build();
    }

    @GetMapping(ClientUrl.SUBJECT_BY_LEVEL_ID + "/{" + Constant.LEVEL_ID + "}")
    public ReturnReponse<List<Subject>> getSubjectsByLevel(@PathVariable(Constant.LEVEL_ID) Long levelId) {
        List<Subject> associatedSubjects = subjectUseCase.getSubjectsByLevel(levelId);
        return ReturnReponse.<List<Subject>>builder().message("data found successfully").succeeded(true).value(associatedSubjects).build();
    }

}