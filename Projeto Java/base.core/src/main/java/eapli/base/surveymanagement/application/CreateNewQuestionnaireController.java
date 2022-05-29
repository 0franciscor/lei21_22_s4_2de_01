package eapli.base.surveymanagement.application;

import eapli.base.surveymanagement.antlr.SurveyLexer;
import eapli.base.surveymanagement.antlr.eapli.base.surveymanagement.antlr.QuestionnaireMain;
import eapli.base.surveymanagement.antlr.eapli.base.surveymanagement.antlr.QuestionnaireVisitor;
import eapli.base.surveymanagement.antlr.eapli.base.surveymanagement.antlr.SurveyParser;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.FileInputStream;
import java.io.IOException;

public class CreateNewQuestionnaireController {
    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    public Questionnaire createQuestionnaire(final String file) throws IOException{
        authz.ensureAuthenticatedUserHasAnyOf(BaseRoles.SALES_MANAGER, BaseRoles.POWER_USER, BaseRoles.ADMIN);

        QuestionnaireMain qmain = new QuestionnaireMain();
        qmain.parseWithVisitor(file);

        return null;

    }
}
