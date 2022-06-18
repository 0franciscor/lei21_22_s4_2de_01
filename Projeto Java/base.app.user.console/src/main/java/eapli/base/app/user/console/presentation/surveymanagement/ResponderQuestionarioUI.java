package eapli.base.app.user.console.presentation.surveymanagement;

import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.surveymanagement.application.ResponderQuestionarioController;
import eapli.base.surveymanagement.domain.Obligatoriness;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.dto.QuestionDTO;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.dto.SectionDTO;
import eapli.base.surveymanagement.dto.SurveyDTO;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Iterator;

public class ResponderQuestionarioUI extends AbstractUI {

    private final ResponderQuestionarioController responderQuestionarioController = new ResponderQuestionarioController();

    private final AuthorizationService authz = AuthzRegistry.authorizationService();

    @Override
    protected boolean doShow() {
        Iterator<QuestionnaireDTO> questionnaires = responderQuestionarioController.getUnansweredCustomerSurveys(authz.session().get().authenticatedUser().email().toString()).iterator();

        if(!questionnaires.hasNext()){
            System.out.println("De momento não existem questionários por responder!");
        } else {
            System.out.println("\nQuestionários por responder\n");
            while (questionnaires.hasNext()) {
                QuestionnaireDTO questionnaire = questionnaires.next();
                System.out.println(questionnaire);
            }
            System.out.println();

            System.out.println("Deseja responder a que questionário?\n");

            String id = Console.readLine("Identifier:");

            SurveyDTO surveyDTO = responderQuestionarioController.getSurvey(id);

            System.out.println("--- Questionário ---\n");
            System.out.printf("Id: %s Título: %s\n\n",surveyDTO.id,surveyDTO.titulo);

            for (SectionDTO sectionDTO: surveyDTO.sections){
                System.out.println("--- Secção ---");
                System.out.printf("Id: %s\n",sectionDTO.sectionId);
                System.out.printf("Título: %s\n",sectionDTO.titulo);

                for (QuestionDTO questionDTO: sectionDTO.questions){
                    System.out.println("--- Pergunta ---");
                }

            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Answer the Questionnaire";
    }
}
