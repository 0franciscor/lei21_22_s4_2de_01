package eapli.base.app.backoffice.console.surveymanagement;

import eapli.base.surveymanagement.application.ObterRelatorioEstatisticoController;
import eapli.base.surveymanagement.domain.Question;
import eapli.base.surveymanagement.domain.Type;
import eapli.base.surveymanagement.dto.QuestionnaireDTO;
import eapli.base.surveymanagement.dto.SurveyDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

import java.util.*;

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
            System.out.println();
            System.out.println("--- Relatório Estatístico ---");
            int clientesInquiridos = obterRelatorioEstatisticoController.obterQuantidadeClientesInquiridos(id);
            System.out.printf("Clientes Inquiridos: %d\n",clientesInquiridos);
            int numRespostas = obterRelatorioEstatisticoController.obterQuantidadeRespostas(id);
            System.out.printf("Quantidade de Respostas: %d\n",numRespostas);
            if (numRespostas>0){
                double percentagemRespostas =((double)numRespostas/(double) clientesInquiridos) * 100;
                System.out.printf("Percentagem de Respostas Obtidas: %.2f\n",percentagemRespostas);
                List<Question> questions = obterRelatorioEstatisticoController.obterQuestoes(id);
                System.out.println("- Perguntas -");
                for (Question question: questions){
                    if (!question.getType().equals(Type.FREE_TEXT) && !question.getType().equals(Type.NUMERIC)){
                        System.out.println();
                        System.out.printf("Id: %d\n",question.getQuestionId());
                        System.out.printf("Tipo: ");
                        System.out.println(question.getType());
                        System.out.println();
                        Map<Long,String> ops = question.getOptions();
                        if (question.getType().equals(Type.SINGLE_CHOICE) || question.getType().equals(Type.MULTIPLE_CHOICE)){
                            if (question.getType().equals(Type.MULTIPLE_CHOICE)){
                                System.out.println("Para cada alternativa:");
                                System.out.println();
                            }
                            for (Long op : ops.keySet()){
                                int num = obterRelatorioEstatisticoController.obterQuantidadeRespostasOpcao(question.getQuestionId(), op,id);
                                percentagemRespostas = ((double) num/(double) numRespostas) * 100;
                                System.out.println("Opção " + op);
                                System.out.printf("Percentagem: %.2f\n",percentagemRespostas);
                            }
                        }
                        if (question.getType().equals(Type.MULTIPLE_CHOICE)){
                            System.out.println();
                            System.out.println("Combinações:");
                            System.out.println();
                            List<Long> combinacao;
                            for (int i = 1; i < ops.size(); i++){
                                combinacao = new ArrayList<>();


                            }
                        }
                    }
                }


            }else{
                System.out.println();
                System.out.println("De momento não é possível obter nenhum relatório estatístico para este questionário!");
            }

        }

        return true;
    }

    @Override
    public String headline() {
        return "Get a statistical report";
    }
}
