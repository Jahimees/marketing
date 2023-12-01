package by.bsuir.marketing.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SurveyController {

    @Value("${page.survey}")
    private String SURVEY_PAGE;

    @GetMapping("/survey/{idBlank}")
    public String openSurveyPage(@PathVariable int idBlank, HttpServletRequest servletRequest) {
        servletRequest.setAttribute("idBlank", idBlank);

        return SURVEY_PAGE;
    }
}
