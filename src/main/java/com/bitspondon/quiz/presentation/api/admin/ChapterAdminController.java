package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.domain.entities.Subject;
import com.bitspondon.quiz.application.usecase.IChapterUseCase;
import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.entities.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("hasRole('ADMIN')")
//@RequestMapping("/api/v1/admin/chapter")
public class ChapterAdminController {

    @Autowired
    private IChapterUseCase chapterUseCase;

    @Autowired
    private ISubjectUseCase subjectUseCase;

    @GetMapping("/chapter/index")
    public String getChapters(Model model) {
//        return chapterUseCase.getChapters();
        model.addAttribute("chapters", chapterUseCase.getChapters());
        return "/chapter/index";
    }

    @GetMapping("/chapter/create")
    public String create(Model model) {
        List<Subject> subjectList = subjectUseCase.getSubjects();
        model.addAttribute("chapter", new Chapter());
        model.addAttribute("subjectOptions", subjectList);
        model.addAttribute("actionUrl", "/chapter/create");

        return "/chapter/create";
    }

    @PostMapping("/chapter/create")
    public String saveChapter(@ModelAttribute("chapter") Chapter chapter) {
        chapterUseCase.saveChapter(chapter);
        return "redirect:/chapter/index";
    }

    @GetMapping("/chapter/edit/{chapterId}")
    public String edit(@PathVariable("chapterId") Long chapterId, Model model) {
//        System.out.println(chapterId);
        List<Subject> subjectList = subjectUseCase.getSubjects();
        Chapter chapter = chapterUseCase.getChapter(chapterId);
        model.addAttribute("chapter", chapter);
        model.addAttribute("actionUrl", "/chapter/edit/" + chapterId);
        model.addAttribute("subjectOptions", subjectList);
        return "/chapter/create";
    }

    @PostMapping("/chapter/edit/{chapterId}")
    public String edit(@PathVariable("chapterId") Long chapterId, @ModelAttribute("chapter") Chapter chapter) {
        chapter.setId(chapterId);
        chapterUseCase.updateChapter(chapter);
        return "redirect:/chapter/index";
    }

//    @DeleteMapping
//    public Chapter deleteChapter(@RequestParam int id) {
//        return chapterUseCase.deleteChapter(id);
//    }
}
