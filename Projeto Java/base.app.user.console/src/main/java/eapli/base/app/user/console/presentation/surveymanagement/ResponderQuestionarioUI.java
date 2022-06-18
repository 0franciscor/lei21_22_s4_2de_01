package eapli.base.app.user.console.presentation.surveymanagement;

import eapli.base.ordermanagement.dto.OrderDTO;
import eapli.base.surveymanagement.application.ResponderQuestionarioController;
import eapli.base.surveymanagement.domain.Obligatoriness;
import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.domain.Type;
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

            System.out.println("\n--- Questionário ---\n");
            System.out.printf("Id: %s Título: %s\n",surveyDTO.id,surveyDTO.titulo);

            for (SectionDTO sectionDTO: surveyDTO.sections){
                System.out.printf("--- Secção %s ---\n",sectionDTO.sectionId);
                System.out.printf("Título: %s\n",sectionDTO.titulo);

                for (QuestionDTO questionDTO: sectionDTO.questions){
                    System.out.printf("--- Pergunta %s ---\n",questionDTO.questionId);
                    System.out.println(questionDTO.pergunta);
                    System.out.printf("\nObrigatoriedade: %s\n",questionDTO.obligatoriness);
                    System.out.printf("Tipo: %s\n",questionDTO.type);
                    boolean responder = true;
                    if (questionDTO.obligatoriness.equals(Obligatoriness.OPTIONAL)){
                        String temp = Console.readLine("Deseja responder? (s|n)");
                        if (temp.equals("n")){
                            responder=false;
                        }
                    }
                    if (responder){
                        if (questionDTO.options != null){
                            int size = questionDTO.options.size();
                            System.out.println();
                            for (int i=0; i<size;i++){
                                System.out.printf("%s) %s;\n",i+1,questionDTO.options.get(i));
                            }
                        }
                        System.out.println();
                        System.out.println(questionDTO.extraInfo);
                        System.out.println();
                        if (questionDTO.type.equals(Type.SINGLE_CHOICE)){
                            boolean valido;
                            do{
                                int option = Console.readInteger("Resposta:");
                                if (option<1 || option>questionDTO.options.size()){
                                    valido=false;
                                    System.out.println("O valor introduzido não corresponde a nenhuma opção válida!");
                                } else {
                                    valido = true;
                                    StringBuilder stringBuilder = new StringBuilder("Single-Choice "+option+"\n\nFIM");
                                    responderQuestionarioController.writeFile(surveyDTO.id,stringBuilder.toString());
                                    responderQuestionarioController.validateAnswer(authz.session().get().authenticatedUser().email().toString(),surveyDTO.id,sectionDTO.sectionId,questionDTO.questionId);
                                }
                            }while (!valido);
                        } else if(questionDTO.type.equals(Type.FREE_TEXT)){
                            String resposta = Console.readLine("Resposta:");
                            StringBuilder stringBuilder = new StringBuilder("Free-Text "+resposta+"\n\nFIM");
                            responderQuestionarioController.writeFile(surveyDTO.id,stringBuilder.toString());
                            responderQuestionarioController.validateAnswer(authz.session().get().authenticatedUser().email().toString(),surveyDTO.id,sectionDTO.sectionId,questionDTO.questionId);
                        }
                    }

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
