package eapli.base.infrastructure.bootstrapers.demo;

import eapli.base.clientmanagement.domain.Client;
import eapli.base.clientmanagement.domain.Email;
import eapli.base.clientmanagement.repositories.ClientRepository;
import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.application.AtribuirQuestionarioAosClientesService;
import eapli.base.surveymanagement.domain.*;
import eapli.base.surveymanagement.repository.SurveyRepository;
import eapli.framework.actions.Action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

public class SurveyBootstrapper extends BaseDemoBootstrapper implements Action {

    private final SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();

    private final ClientRepository clientRepository = PersistenceContext.repositories().client();

    @Override
    public boolean execute() {
        createSurveys();
        return true;
    }

    private void createSurveys(){

        AtribuirQuestionarioAosClientesService service = new AtribuirQuestionarioAosClientesService();

        Questionnaire questionnaire = new Questionnaire();

        questionnaire.modifyId(new Identifier("01-SAT2"));
        questionnaire.modifyTitle(new Titulo("Customer Satisfaction"));
        questionnaire.modifyInitialMessage(new Message("Hello!\nPlease take a few minutes of your time to complete the following questionnaire."));

        Section section = new Section();
        section.modifyId(1L);
        section.modifyTitle(new Titulo("Service feedback"));
        section.modifyObligatoriness("mandatory");

        Question question = new Question();
        question.modifyId(1L);
        question.modifyPergunta(new Message("How is the quality of our services compared to other companies?"));
        question.modifyObligatoriness("mandatory");
        question.modifyType("Single-Choice");
        question.addOption(1L,"Much better");
        question.addOption(2L,"A bit better");
        question.addOption(3L,"Equal");
        question.addOption(4L,"A bit worse");
        question.addOption(5L,"Much worse");
        question.modifyExtraInfo(new Message("Answer the question by selecting one (and only one) of the options provided."));
        section.addQuestion(question);

        Question question1 = new Question();
        question1.modifyId(2L);
        question1.modifyPergunta(new Message("What changes can the company make to improve the quality of its services?"));
        question1.modifyObligatoriness("optional");
        question1.modifyType("Free-Text");
        question1.modifyExtraInfo(new Message("Answer the question by typing some text."));
        section.addQuestion(question1);

        questionnaire.addSection(section);

        questionnaire.modifyFinalMessage(new Message("Thank you for answering the questionnaire!"));

        Calendar initialDate = Calendar.getInstance();
        initialDate.set(2022, 5, 12);
        Calendar finalDate = Calendar.getInstance();
        finalDate.set(2022, 7, 1);
        Period period = new Period(initialDate,finalDate);

        questionnaire.modifyPeriod(period);

        questionnaire.modifyRestricao(new Restricao(Criterio.NONE));

        surveyRepository.save(questionnaire);

        Optional<Client> client = clientRepository.findByEmail(Email.valueOf("arianasobral26@outlook.pt"));

        client.get().addUnansweredQuestionnaire(surveyRepository.findByIdentifier(questionnaire.getSurveyId()).get());

        clientRepository.save(client.get());

    }
}
