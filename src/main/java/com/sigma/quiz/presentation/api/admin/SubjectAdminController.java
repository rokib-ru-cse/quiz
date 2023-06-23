package com.sigma.quiz.presentation.api.admin;

import com.sigma.quiz.application.usecase.ISubjectUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/subject")
public class SubjectAdminController {

    @Autowired
    private ISubjectUseCase subjectUseCase;

    @GetMapping
    public ReturnReponse<Subject> getSubjects(){
        return subjectUseCase.getSubjects();
    }

    @PostMapping
    public ReturnReponse<Subject> saveSubject(Subject subjectRequest){
        return subjectUseCase.saveSubject(subjectRequest);
    }

    @PutMapping
    public ReturnReponse<Subject> updateSubject(Subject subjectRequest){
        return subjectUseCase.updateSubject(subjectRequest);
    }

    @DeleteMapping
    public ReturnReponse<Subject> deleteSubject(@RequestParam int id){
        return subjectUseCase.deleteSubject(id);
    }



}
