package eapli.base.surveymanagement.antlr.eapli.base.surveymanagement.antlr;

import eapli.base.surveymanagement.domain.*;

/**
 *
 *
 * Created by Rita Ariana Sobral on 02/06/2022.
 */
public class AnswersVisitor extends AnswerBaseVisitor<Answer> {

    String aux;
    Answer answer = new Answer();


    @Override public Answer visitStart(AnswerParser.StartContext ctx) {
        visitType(ctx.type());
        return answer;
    }

    @Override public Answer visitType(AnswerParser.TypeContext ctx) {
        if (ctx.FREE_TEXT() != null){

        }
        else if (ctx.NUMERIC() != null){

        }
        else{
            if (ctx.SINGLE_CHOICE() != null){
                answer.modifyType("Single-Choice");
            }
            for (int i = 0; i < ctx.opcao().size(); i++){
                visit(ctx.opcao(i));
                answer.addAnswer(aux);
            }
            if (ctx.frase() != null){
                visit(ctx.frase());
                answer.addAnswer(aux);
            }
        }

        return answer;
    }

    @Override public Answer visitFrase(AnswerParser.FraseContext ctx) {
        return null;
    }

    @Override public Answer visitOpcao(AnswerParser.OpcaoContext ctx) {
        visit(ctx.alfanumerico());
        return answer;
    }

    @Override public Answer visitAlfanumerico(AnswerParser.AlfanumericoContext ctx) {
        if (ctx.NUMERO() != null){
            aux = ctx.NUMERO().getText();
        }
        else{
            aux = ctx.PALAVRA().getText();
        }
        return answer;
    }


}
