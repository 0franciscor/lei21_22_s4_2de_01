package eapli.base.app.backoffice.console.surveymanagement;

import eapli.base.AGV.domain.AGVId;
import eapli.base.AGV.dto.AgvDto;
import eapli.base.productmanagement.dto.ProductOrderDto;
import eapli.base.surveymanagement.application.CreateNewQuestionnaireController;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.List;

/**
 * UI for register a client order to the application.
 *
 * Created by 1201382@isep.ipp.pt
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

        System.out.println("File path entered Successfuly");
        return true;

    }

    @Override
    public String headline() {
        return "Create New Questionnaire";
    }

}
