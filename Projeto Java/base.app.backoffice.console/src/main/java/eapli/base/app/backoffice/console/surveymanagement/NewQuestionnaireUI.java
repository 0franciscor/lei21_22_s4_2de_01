package eapli.base.app.backoffice.console.surveymanagement;

import eapli.base.surveymanagement.application.CreateNewQuestionnaireController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Calendar;


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
                Calendar finalDate = Console.readCalendar("Please enter until what date it is possible to answer the questionnaire (yyyy/MM/dd):","yyyy/MM/dd");
                createNewQuestionnaireController.createQuestionnaire(fileName,finalDate);

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
