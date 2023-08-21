package com.bitspondon.quiz.presentation.api.client;

import com.bitspondon.quiz.domain.constant.Constant;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Controller
@PreAuthorize("hasRole('" + Constant.ROLE_USER + "')")
public class LiveQuizClientController {




}
