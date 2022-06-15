package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.dto.QuestionnaireDTO;

public class ResponderQuestionarioController {

    private final ResponderQuestionarioService service = new ResponderQuestionarioService();

    public Iterable<QuestionnaireDTO> getUnansweredCustomerSurveys(String clientEmail) {
        return service.getUnansweredCustomerSurveys(clientEmail);
    }

    public QuestionnaireDTO findQuestionnaire(String id, String toString) {
        return null;
    }
}
