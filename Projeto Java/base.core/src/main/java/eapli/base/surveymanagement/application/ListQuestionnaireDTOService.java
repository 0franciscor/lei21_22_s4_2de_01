package eapli.base.surveymanagement.application;


import eapli.base.clientmanagement.domain.Client;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.productmanagement.domain.Product;
import eapli.base.productmanagement.dto.ProductDTO;
import eapli.base.productmanagement.repository.ProductRepository;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.Survey;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * An application service to avoid code duplication.
 *
 * @author Rita Ariana Sobral
 *
 */
public class ListQuestionnaireDTOService {

    private final ProductRepository productRepository = PersistenceContext.repositories().products();

    public Iterable<QuestionnaireDTO> getUnansweredSurveys(Client client) {

        final Iterable<Survey> questionnaires = client.getUnansweredQuestionnaires();

        final List<Questionnaire> questionnairesList = new ArrayList<>();

        for (Survey survey: questionnaires){
            questionnairesList.add(survey.questionnaire());
        }

        // transform for the presentation layer
        final List<QuestionnaireDTO> ret = new ArrayList<>();
        questionnairesList.forEach(e -> ret.add(e.toDTO()));
        return ret;
    }
}
