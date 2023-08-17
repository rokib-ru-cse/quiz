package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.Subject;

import java.util.List;

public interface ISubjectUseCase {
    List<Subject> getSubjects();
    Subject getSubject(Long subjectId);

    Subject saveSubject(Subject subjectRequest);
    Subject updateSubject(Subject subjectRequest);
    Subject deleteSubject(Long id);
}
