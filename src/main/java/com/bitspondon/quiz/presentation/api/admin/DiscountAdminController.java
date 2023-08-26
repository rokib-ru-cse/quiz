package com.bitspondon.quiz.presentation.api.admin;

import com.bitspondon.quiz.application.usecase.ILiveQuizUseCase;
import com.bitspondon.quiz.domain.ReturnReponse;
import com.bitspondon.quiz.domain.constant.Constant;
import com.bitspondon.quiz.domain.entities.LiveQuiz;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(Constant.CURRENT_API_VERSION+"/livequizwithdiscount")
public class DiscountAdminController {


    private final FeatureManager featureManager;
    private final ILiveQuizUseCase liveQuizUseCase;
    public static final Feature DISCOUNT_APPLIED = new NamedFeature("DISCOUNT_APPLIED");


    @GetMapping
    public ReturnReponse<List<LiveQuiz>> getLiveQuizes() {
        List<LiveQuiz> liveQuizzes = null;
        if (featureManager.isActive(DISCOUNT_APPLIED)) {
            liveQuizzes = liveQuizUseCase.getLiveQuizzes();
        } else {
            liveQuizzes = liveQuizUseCase.getLiveQuizzes();
        }
        return ReturnReponse.<List<LiveQuiz>>builder().message("data found successfully").succeeded(true).value(liveQuizzes).build();

    }

}
