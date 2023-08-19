package com.bitspondon.quiz.application.usecase;

import com.bitspondon.quiz.domain.entities.Subject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISubjectUseCase {
    List<Subject> getSubjects();
    Subject getSubject(Long subjectId);

    Subject saveSubject(Subject subjectRequest);
    List<Subject> saveSubjects(MultipartFile file) throws Exception;

    Subject updateSubject(Subject subjectRequest);
    Subject deleteSubject(Long id);
}
