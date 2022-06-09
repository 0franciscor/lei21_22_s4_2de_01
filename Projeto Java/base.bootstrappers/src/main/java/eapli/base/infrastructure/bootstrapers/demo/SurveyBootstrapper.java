package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.*;
import eapli.base.surveymanagement.repository.SurveyRepository;
import eapli.framework.actions.Action;

public class SurveyBootstrapper extends BaseDemoBootstrapper implements Action {

    private final SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();

    @Override
    public boolean execute() {
        createSurveys();
        return true;
    }

    private void createSurveys(){

        Questionnaire questionnaire = new Questionnaire();

        questionnaire.modifyId(new Identifier("01-SAT2022"));
        questionnaire.modifyTitle(new Titulo("Satisfação do cliente"));
        questionnaire.modifyInitialMessage(new Message("Bom dia!\nDedique, por favor, alguns minutos do seu tempo para preencher o questionário seguinte."));

        Section section = new Section();
        section.modifyId(1L);
        section.modifyTitle(new Titulo("Feedback do atendimento"));
        section.modifyObligatoriness("mandatory");

        Question question = new Question();
        question.modifyId(1L);
        section.addQuestion(question);

        questionnaire.addSection(section);

        questionnaire.modifyFinalMessage(new Message("Obrigado por responder ao questionário!"));

        surveyRepository.save(questionnaire);

    }
}
