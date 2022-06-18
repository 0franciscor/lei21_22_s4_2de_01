package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.dto.SurveyDTO;

public class ResponderQuestionarioController {

    private final ResponderQuestionarioService service = new ResponderQuestionarioService();

    public Iterable<QuestionnaireDTO> getUnansweredCustomerSurveys(String clientEmail) {
        return service.getUnansweredCustomerSurveys(clientEmail);
    }

    public SurveyDTO getSurvey(String surveyId){
        return service.getSurvey(surveyId);
    }
}
