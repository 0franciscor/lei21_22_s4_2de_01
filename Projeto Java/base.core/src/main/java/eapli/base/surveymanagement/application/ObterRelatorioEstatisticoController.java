package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.dto.QuestionnaireDTO;

import java.util.Iterator;

public class ObterRelatorioEstatisticoController {

    private ListQuestionnaireDTOService service = new ListQuestionnaireDTOService();

    public Iterable<QuestionnaireDTO> findAllQuestionnaires() {
        return service.getSurveys();
    }
}
