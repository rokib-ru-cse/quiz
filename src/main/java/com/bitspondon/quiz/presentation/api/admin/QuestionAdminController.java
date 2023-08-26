package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.IChapterUseCase;
import com.bitspondon.quiz.application.usecase.ILevelUseCase;
import com.bitspondon.quiz.application.usecase.IQuestionUseCase;
import com.bitspondon.quiz.application.usecase.ISubjectUseCase;
import com.bitspondon.quiz.domain.constant.AdminUrl;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.constant.TemplatesPath;
import com.bitspondon.quiz.domain.dto.question.OptionDTO;
import com.bitspondon.quiz.domain.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class QuestionAdminController {

    @Autowired
    private IQuestionUseCase questionUseCase;
    @Autowired
    private IChapterUseCase chapterUseCase;
    @Autowired

    private ISubjectUseCase subjectUseCase;
    @Autowired
    private ILevelUseCase levelUseCase;


    @GetMapping(AdminUrl.QUESTION_INDEX)
    public ModelAndView getQuestions() {
        ModelAndView model = new ModelAndView(TemplatesPath.QUESTION_INDEX_PAGE);
        Constant constants = new Constant();
        constants.setQuestionList(questionUseCase.getQuestions());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @GetMapping(AdminUrl.QUESTION_CREATE)
    public ModelAndView create() {
        ModelAndView model = new ModelAndView(TemplatesPath.QUESTION_CREATE_PAGE);
        Constant constants = new Constant();
        constants.setLevelList(levelUseCase.getLevels());
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setChapterList(chapterUseCase.getChapters());
        constants.setActionUrl(AdminUrl.QUESTION_CREATE);
        model.addObject(Constant.QUESTION, new Question());
        model.addObject(Constant.QUESTION_OPTIONS, new ArrayList<OptionDTO>());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }

    @PostMapping(AdminUrl.QUESTION_CREATE)
    public String saveQuestion(@ModelAttribute(Constant.QUESTION) Question question) {
        questionUseCase.saveQuestion(question);
        return AdminUrl.QUESTION_REDIRECT_TO_INDEX;
    }


    @PostMapping(AdminUrl.QUESTION_UPLOAD)
    public String saveQuestions(@ModelAttribute(Constant.MULTIPART_FILE_REQUEST_PARAM_NAME) MultipartFile file) throws Exception {
        questionUseCase.saveQuestions(file);
        return AdminUrl.QUESTION_REDIRECT_TO_INDEX;
    }


//    @PostMapping(AdminUrl.QUESTION_UPLOAD)
//    public String saveQuestions(@ModelAttribute(Constant.MULTIPART_FILE_REQUEST_PARAM_NAME) MultipartFile file) throws Exception {
//
//
//        final String TEMP_STORAGE = "C:\\rokibrucse\\project\\quiz\\src\\main\\resources\\files\\";
//
//
//        String originalFileName = file.getOriginalFilename();
//        File fileToImport = new File(TEMP_STORAGE + originalFileName);
//
//        file.transferTo(fileToImport);
//
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addString("fullPathFileName", TEMP_STORAGE + originalFileName)
//                .addLong("startAt", System.currentTimeMillis())
//                .toJobParameters();
//
//
//        try {
//            JobExecution jobExecution = jobLauncher.run(importJob, jobParameters);
//
//            if (jobExecution.getExitStatus().getExitCode().equals(ExitStatus.COMPLETED)) {
//                // delete the file
//                Files.deleteIfExists(Paths.get(TEMP_STORAGE + originalFileName));
//            }
//
//        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException |
//                 JobParametersInvalidException e) {
//            e.printStackTrace();
//        }
////        //questionUseCase.saveQuestions(file);
//        return AdminUrl.QUESTION_REDIRECT_TO_INDEX;
//    }


//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadQuestions(@RequestParam("file") MultipartFile file) {
//        try {
//            // Save the uploaded file to a temporary location
//            Path tempFile = Files.createTempFile("questions", ".xlsx");
//            file.transferTo(tempFile.toFile());
//
//            // Start the Spring Batch job
//            JobParameters jobParameters = new JobParametersBuilder()
//                    .addString("input.file.name", tempFile.toString())
//                    .toJobParameters();
//            jobLauncher.run(importJob, jobParameters);
//
//            return ResponseEntity.ok("File uploaded and processing started.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the file.");
//        }
//    }

//    @PostMapping("/upload")
//    public ResponseEntity<String> uploadQuestions(@RequestParam("file") MultipartFile file) {
//        questionUseCase.processQuestionExcelFile(file);
//        return ResponseEntity.ok("File uploaded and processing started.");
//    }


    @GetMapping(AdminUrl.QUESTION_EDIT + "/{" + Constant.QUESTION_ID + "}")
    public ModelAndView edit(@PathVariable(Constant.QUESTION_ID) Long questionId) {
        ModelAndView model = new ModelAndView(TemplatesPath.QUESTION_CREATE_PAGE);
        Question question = questionUseCase.getQuestion(questionId);
        Constant constants = new Constant();
        constants.setLevelList(levelUseCase.getLevels());
        constants.setSubjectList(subjectUseCase.getSubjects());
        constants.setChapterList(chapterUseCase.getChapters());
        constants.setActionUrl(AdminUrl.QUESTION_EDIT + "/" + questionId);
        model.addObject(Constant.QUESTION, question);
        model.addObject(Constant.QUESTION_OPTIONS, question.getOptionList());
        model.addObject(Constant.CONSTANTS, constants);
        return model;
    }


    @PostMapping(AdminUrl.QUESTION_EDIT + "/{" + Constant.QUESTION_ID + "}")
    public String editQuestion(@PathVariable(Constant.QUESTION_ID) Long questionId, @ModelAttribute(Constant.QUESTION) Question question) {
        question.setId(questionId);
        questionUseCase.updateQuestion(question);
        return AdminUrl.QUESTION_REDIRECT_TO_INDEX;
    }
}
