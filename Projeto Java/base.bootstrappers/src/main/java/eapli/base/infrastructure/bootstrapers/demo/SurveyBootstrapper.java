package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.domain.*;
import eapli.base.surveymanagement.repository.SurveyRepository;
import eapli.framework.actions.Action;

import java.util.Calendar;

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
        question.modifyPergunta(new Message("Como é a qualidade dos nossos serviços em comparação com as outras empresas?"));
        question.modifyObligatoriness("mandatory");
        question.modifyType("Single-Choice");
        question.addOption(1L,"Muito melhor");
        question.addOption(2L,"Um bocado melhor");
        question.addOption(3L,"Igual");
        question.addOption(4L,"Um bocado pior");
        question.addOption(5L,"Muito pior");
        question.modifyExtraInfo(new Message("Escolha uma das seguintes frases."));
        section.addQuestion(question);

        questionnaire.addSection(section);

        questionnaire.modifyFinalMessage(new Message("Obrigado por responder ao questionário!"));

        Calendar initialDate = Calendar.getInstance();
        initialDate.set(2022, 5, 12);
        Calendar finalDate = Calendar.getInstance();
        finalDate.set(2022, 7, 1);
        Period period = new Period(initialDate,finalDate);

        questionnaire.modifyPeriod(period);

        surveyRepository.save(questionnaire);

    }
}
