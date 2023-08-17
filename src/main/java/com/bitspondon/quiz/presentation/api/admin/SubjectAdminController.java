package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.domain.entities.Subject;
import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.entities.Level;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
//@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//@RequestMapping("/api/v1/admin/subject")
//@PreAuthorize("hasRole('ADMIN')") == @PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class SubjectAdminController {

    @Autowired
    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;


    @GetMapping("/subject/index")
    public ModelAndView getSubjects() {
        ModelAndView model = new ModelAndView("/subject/index");
        model.addObject("subjects", subjectUseCase.getSubjects());
        return model;
    }

    @GetMapping("/subject/create")
    public String create(Model model) {
        List<Level> levelList = levelUseCase.getLevels();
        model.addAttribute("subject", new Subject());
        model.addAttribute("levelOptions", levelList);

        model.addAttribute("actionUrl", "/subject/create");
        return "/subject/create";
    }

    //    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/subject/create")
    public String saveSubject(@ModelAttribute("subject") Subject subject) {
        subjectUseCase.saveSubject(subject);
        return "redirect:/subject/index";
    }

    @GetMapping("/subject/edit/{subjectId}")
//    public String edit(@RequestParam("levelId") int levelId) {
    public String edit(@PathVariable("subjectId") Long subjectId, Model model) {
        Subject subject = subjectUseCase.getSubject(subjectId);
        List<Level> levelList = levelUseCase.getLevels();

        model.addAttribute("subject", subject);
        model.addAttribute("levelOptions", levelList);
        model.addAttribute("actionUrl", "/subject/edit/" + subjectId);
        return "/subject/create";
    }

    @PostMapping("/subject/edit/{subjectId}")
    public String edit(@PathVariable("subjectId") Long subjectId, @ModelAttribute("subject") Subject subject) {
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
