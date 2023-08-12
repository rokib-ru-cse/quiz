package com.sigma.quiz.application.usecase.impl;

import com.sigma.quiz.application.repository.ILevelRepository;
import com.sigma.quiz.application.repository.ISubjectRepository;
import com.sigma.quiz.application.usecase.ISubjectUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.Util;
import com.sigma.quiz.domain.entities.Level;
import com.sigma.quiz.domain.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SubjectUseCase implements ISubjectUseCase {

    @Autowired
    private ILevelRepository levelRepository;
    @Autowired
    private ISubjectRepository subjectRepository;

    @Override
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
//        return ReturnReponse.<Subject>builder().message("Data Got Successfully").succeeded(true).values(subjectList).build();
    }

    @Override
    public Subject getSubject(int subjectId) {
        return subjectRepository.findById(subjectId).get();
    }

    @Override
    public Subject saveSubject(Subject subjectRequest) {
//        Level level = levelRepository.findById(subjectRequest.getLevelId()).get();
        subjectRequest.setUpdatedAt(new Date());
        subjectRequest.setCreatedAt(new Date());
//        subjectRequest.setLevel(level);
        Subject savedSubject = subjectRepository.save(subjectRequest);
        return savedSubject;
//        return  ReturnReponse.<Subject>builder().message("Subject Saved Successfully").succeeded(true).value(savedSubject).build();
    }

    @Override
    public Subject updateSubject(Subject subjectRequest) {
        Subject dbSubject = subjectRepository.findById(subjectRequest.getId()).get();
//        Level level = levelRepository.findById(subjectRequest.getLevelId()).get();
        if (!Util.isNullOrWhiteSpace(subjectRequest.getName())) {
            dbSubject.setName(subjectRequest.getName());
        }
        dbSubject.setIcon(subjectRequest.getIcon());
        dbSubject.setImage(subjectRequest.getImage());
        dbSubject.setActive(subjectRequest.isActive());
        dbSubject.setLevel(subjectRequest.getLevel());
        dbSubject.setUpdatedAt(new Date());
//        dbSubject.setLevel(level);
        Subject updatedSubject = subjectRepository.save(dbSubject);
        return updatedSubject;
//       return ReturnReponse.<Subject>builder().message("Subject Updated successfully").succeeded(true).value(updatedSubject).build();
    }

    @Override
    public Subject deleteSubject(int id) {

        Subject dbSubject = subjectRepository.findById(id).get();
        subjectRepository.delete(dbSubject);
        return dbSubject;
//        return ReturnReponse.<Subject>builder().message("Subject deleted successfully").succeeded(true).value(dbSubject).build();
    }
}
