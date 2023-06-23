package com.sigma.quiz.application.usecase;

import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Subject;
import org.springframework.stereotype.Component;

public interface ISubjectUseCase {
    ReturnReponse<Subject> getSubjects();
    ReturnReponse<Subject> saveSubject(Subject subjectRequest);
    ReturnReponse<Subject> updateSubject(Subject subjectRequest);
    ReturnReponse<Subject> deleteSubject(int id);
}
