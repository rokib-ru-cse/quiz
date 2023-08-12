package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Subject;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ISubjectUseCase {
    List<Subject> getSubjects();
    Subject getSubject(int subjectId);

    Subject saveSubject(Subject subjectRequest);
    Subject updateSubject(Subject subjectRequest);
    Subject deleteSubject(int id);
}
