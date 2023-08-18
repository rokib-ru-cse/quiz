package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.IChapterUseCase;
import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.constant.AdminUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.Chapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("hasRole('" + Constant.ROLE_ADMIN + "')")
public class ChapterAdminController {

    @Autowired
    private IChapterUseCase chapterUseCase;

    @Autowired
    private ISubjectUseCase subjectUseCase;

    @GetMapping(AdminUrl.CHAPTER_INDEX)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView(AdminUrl.CHAPTER_INDEX);
        Constant constants = new Constant();
        constants.setChapterList(chapterUseCase.getChapters());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }


    @GetMapping(AdminUrl.CHAPTER_CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(AdminUrl.CHAPTER_CREATE);
        Constant constants = new Constant();
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setActionUrl(AdminUrl.CHAPTER_CREATE);
        model.addObject(Constant.CHAPTER, new Chapter());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.CHAPTER_CREATE)
    public String saveChapter(@ModelAttribute(Constant.CHAPTER) Chapter chapter) {
        chapterUseCase.saveChapter(chapter);
        return AdminUrl.CHAPTER_REDIRECT_TO_INDEX;
    }

    @GetMapping(AdminUrl.CHAPTER_EDIT + "/{" + Constant.CHAPTER_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.CHAPTER_ID) Long chapterId) {
        ModelAndView model = new ModelAndView(AdminUrl.CHAPTER_CREATE);
        Constant constants = new Constant();
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setActionUrl(AdminUrl.CHAPTER_EDIT + "/" + chapterId);
        model.addObject(Constant.CHAPTER, chapterUseCase.getChapter(chapterId));
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.CHAPTER_EDIT + "/{" + Constant.CHAPTER_ID + "}")
    public String edit(@PathVariable(Constant.CHAPTER_ID) Long chapterId, @ModelAttribute(Constant.CHAPTER) Chapter chapter) {
        chapter.setId(chapterId);
        chapterUseCase.updateChapter(chapter);
        return AdminUrl.CHAPTER_REDIRECT_TO_INDEX;
    }

}
