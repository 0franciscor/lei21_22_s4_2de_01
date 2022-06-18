package eapli.base.app.backoffice.console.surveymanagement;

import eapli.base.surveymanagement.application.ObterRelatorioEstatisticoController;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.Iterator;

/**
 *
 *
 * Created by 1201382@isep.ipp.pt & 1201239@isep.ipp.pt & 1201386@isep.ipp.pt
 */
public class ObterRelatorioEstatisticoUI extends AbstractUI {

    ObterRelatorioEstatisticoController obterRelatorioEstatisticoController= new ObterRelatorioEstatisticoController();
    @Override
    protected boolean doShow() {
        Iterator<QuestionnaireDTO> questionnaires = obterRelatorioEstatisticoController.findAllQuestionnaires().iterator();

        if(!questionnaires.hasNext()){
            System.out.println("De momento não existem questionários!");
        } else {
            System.out.println("\nQuestionários para analisar\n");
            while (questionnaires.hasNext()) {
                QuestionnaireDTO questionnaire = questionnaires.next();
                System.out.println(questionnaire);
            }
            System.out.println();

            System.out.println("Deseja obter um relatório estatístico de que questionário?\n");

            String id = Console.readLine("Identifier:");
        }

        return true;
    }

    @Override
    public String headline() {
        return "Get a statistical report";
    }
}
