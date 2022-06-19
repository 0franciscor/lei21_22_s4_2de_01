package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.*;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.repository.AnswerRepository;
import eapli.base.surveymanagement.repository.SurveyRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ObterRelatorioEstatisticoController {

    private ListQuestionnaireDTOService service = new ListQuestionnaireDTOService();
    private SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();
    private AnswerRepository answerRepository = PersistenceContext.repositories().answers();

    public Iterable<QuestionnaireDTO> findAllQuestionnaires() {
        return service.getSurveys();
    }

    public int obterQuantidadeClientesInquiridos(String id) {
        Optional<Questionnaire> questionnaire = surveyRepository.findByIdentifier(new Identifier(id));
        return questionnaire.get().getQuantityInquiredCustomers();
    }

    public int obterQuantidadeRespostas(String id) {
        Optional<Questionnaire> questionnaire = surveyRepository.findByIdentifier(new Identifier(id));
        return questionnaire.get().getAmountAnswered();
    }

    public List<Question> obterQuestoes(String id) {
        Optional<Questionnaire> questionnaire = surveyRepository.findByIdentifier(new Identifier(id));
        List<Question> questions = new ArrayList<>();
        for (Section section : questionnaire.get().getSections()){
            for (Question question: section.getQuestions()){
                questions.add(question);
            }
        }
        return questions;
    }

    public int obterQuantidadeRespostasOpcao(Long questionId, Long op, String surveyId) {
        int num = 0;
        for (Answer answer : answerRepository.findAll()){
            if (answer.getIdSurvey().toString().equals(surveyId)){
                if (answer.getIdQuestion().equals(questionId)){
                    for (String as : answer.getRespostas()){
                        if (op.equals(Long.parseLong(as))){
                            num++;
                        }
                    }
                }
            }
        }
        return num;
    }


    public int getNumberAnswerClientQuestion(Long questionId, String surveyId){
        int num = 0;
        for (Answer answer : answerRepository.findAll()){
            if (answer.getIdSurvey().toString().equals(surveyId)){
                if (answer.getIdQuestion().equals(questionId)) {
                    num++;
                }
            }
        }
        return num;
    }
}
