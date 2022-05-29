package eapli.base.app.backoffice.console.surveymanagement;

import eapli.base.surveymanagement.application.CreateNewQuestionnaireController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;


/**
 * UI for register a client order to the application.
 *
 * Created by 1201382@isep.ipp.pt & 1201239@isep.ipp.pt
 */
public class NewQuestionnaireUI extends AbstractUI {

    private final CreateNewQuestionnaireController createNewQuestionnaireController = new CreateNewQuestionnaireController();

    private Boolean operationError=false;


    @Override
    protected boolean doShow() {

        do{
            try {
                String fileName = Console.readLine("Please insert the file path:");
                createNewQuestionnaireController.createQuestionnaire(fileName);

            } catch(Exception e){
                System.out.println(e.getMessage());
                operationError=true;
            }

        } while(operationError);

        return true;

    }

    @Override
    public String headline() {
        return "Create New Questionnaire";
    }

}
