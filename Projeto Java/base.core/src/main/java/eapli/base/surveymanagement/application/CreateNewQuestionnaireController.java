package eapli.base.surveymanagement.application;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.surveymanagement.antlr.eapli.base.surveymanagement.antlr.QuestionnaireMain;
import eapli.base.surveymanagement.domain.Period;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repository.SurveyRepository;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.util.Calendar;

public class CreateNewQuestionnaireController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final SurveyRepository surveyRepository = PersistenceContext.repositories().surveys();


    public Questionnaire createQuestionnaire(final String file, final Calendar finalDate) throws IOException{
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_MANAGER, BaseRoles.POWER_USER, BaseRoles.ADMIN);

        QuestionnaireMain qmain = new QuestionnaireMain();
        Questionnaire questionnaire = qmain.parseWithVisitor(file);
        Period period = new Period(finalDate);
        questionnaire.modifyPeriod(period);
        surveyRepository.save(questionnaire);
        return null;

    }
}
