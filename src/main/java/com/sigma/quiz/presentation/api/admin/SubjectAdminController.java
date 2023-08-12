package com.sigma.quiz.presentation.api.admin;

import com.sigma.quiz.application.usecase.ILevelUseCase;
import com.sigma.quiz.application.usecase.ISubjectUseCase;
import com.sigma.quiz.domain.ReturnReponse;
import com.sigma.quiz.domain.entities.Level;
import com.sigma.quiz.domain.entities.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/api/v1/admin/subject")
public class SubjectAdminController {

    @Autowired
    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;


    @GetMapping("/subject/index")
    public String getSubjects(Model model) {
        model.addAttribute("subjects", subjectUseCase.getSubjects());
        return "/subject/index";
    }

    @GetMapping("/subject/create")
    public String create(Model model) {
        List<Level> levelList = levelUseCase.getLevels();
        model.addAttribute("subject", new Subject());
        model.addAttribute("levelOptions", levelList);

        model.addAttribute("actionUrl", "/subject/create");
        return "/subject/create";
    }

    @PostMapping("/subject/create")
    public String saveSubject(@ModelAttribute("subject") Subject subject) {
        subjectUseCase.saveSubject(subject);
        return "redirect:/subject/index";
    }

    @GetMapping("/subject/edit/{subjectId}")
//    public String edit(@RequestParam("levelId") int levelId) {
    public String edit(@PathVariable("subjectId") int subjectId, Model model) {
        Subject subject = subjectUseCase.getSubject(subjectId);
        List<Level> levelList = levelUseCase.getLevels();

        model.addAttribute("subject", subject);
        model.addAttribute("levelOptions", levelList);
        model.addAttribute("actionUrl", "/subject/edit/" + subjectId);
        return "/subject/create";
    }

    @PostMapping("/subject/edit/{subjectId}")
    public String edit(@PathVariable("subjectId") int subjectId, @ModelAttribute("subject") Subject subject) {
//        System.out.println(level.toString());
        subject.setId(subjectId);
        subjectUseCase.updateSubject(subject);
        return "redirect:/subject/index";
    }

//    @DeleteMapping
//    public Subject deleteSubject(@RequestParam int id) {
//        return subjectUseCase.deleteSubject(id);
//    }


}
