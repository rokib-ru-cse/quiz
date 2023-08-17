//package com.bitspondon.quiz.presentation.api.admin;
//
//import com.bitspondon.quiz.application.usecase.IChapterUseCase;
//import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
//import com.bitspondon.quiz.domain.constant.AdminUrl;
//import com.bitspondon.quiz.domain.constant.Constant;
//import com.bitspondon.quiz.domain.entities.Chapter;
//import com.bitspondon.quiz.domain.entities.Subject;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;
//
//import java.util.List;
//
//@Controller
//@PreAuthorize("hasRole('" + Constant.ROLE_ADMIN + "')")
//public class ChapterAdminController {
//
//    @Autowired
//    private IChapterUseCase chapterUseCase;
//
//    @Autowired
//    private ISubjectUseCase subjectUseCase;
//
//    @GetMapping(AdminUrl.Chapter.INDEX)
//    public ModelAndView getChapters() {
//        ModelAndView model = new ModelAndView(AdminUrl.Chapter.INDEX);
//        model.addObject(Constant.Subject.SUBJECT_LIST, chapterUseCase.getChapters());
//        return model;
//    }
//
//    @GetMapping(AdminUrl.Chapter.CREATE)
//    public ModelAndView create() {
//        ModelAndView model = new ModelAndView(AdminUrl.Chapter.CREATE);
//        List<Subject> subjectList = subjectUseCase.getSubjects();
//        model.addObject(Constant.Chapter.CHAPTER, new Chapter());
//        model.addObject(Constant.Subject.SUBJECT_LIST, subjectList);
//        model.addObject(Constant.ACTION_URL, AdminUrl.Chapter.CREATE);
//        return model;
//    }
//
//    @PostMapping(AdminUrl.Chapter.CREATE)
//    public String saveChapter(@ModelAttribute(Constant.Chapter.CHAPTER) Chapter chapter) {
//        chapterUseCase.saveChapter(chapter);
//        return AdminUrl.Chapter.REDIRECT_TO_INDEX;
//    }
//
//    @GetMapping(AdminUrl.Chapter.EDIT + "/{" + Constant.Chapter.CHAPTER_ID + "}")
//    public ModelAndView edit(@PathVariable(Constant.Chapter.CHAPTER_ID) Long chapterId) {
//        ModelAndView model = new ModelAndView(AdminUrl.Chapter.CREATE);
//        List<Subject> subjectList = subjectUseCase.getSubjects();
//        Chapter chapter = chapterUseCase.getChapter(chapterId);
//        model.addObject(Constant.Chapter.CHAPTER, chapter);
//        model.addObject(Constant.Subject.SUBJECT_LIST, subjectList);
//        model.addObject(Constant.ACTION_URL, AdminUrl.Chapter.CREATE + "/" + chapterId);
//        return model;
//    }
//
//    @PostMapping(AdminUrl.Chapter.EDIT + "/{" + Constant.Chapter.CHAPTER_ID + "}")
//    public String edit(@PathVariable(Constant.Chapter.CHAPTER_ID) Long chapterId, @ModelAttribute(Constant.Chapter.CHAPTER) Chapter chapter) {
//        chapter.setId(chapterId);
//        chapterUseCase.updateChapter(chapter);
//        return AdminUrl.Chapter.REDIRECT_TO_INDEX;
//    }
//
//}
