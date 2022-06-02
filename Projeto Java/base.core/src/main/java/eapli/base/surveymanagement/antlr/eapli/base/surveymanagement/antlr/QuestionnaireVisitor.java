package eapli.base.surveymanagement.antlr.eapli.base.surveymanagement.antlr;


import eapli.base.surveymanagement.domain.*;

/**
 *
 *
 * Created by Rita Ariana Sobral on 02/06/2022.
 */
public class QuestionnaireVisitor extends SurveyBaseVisitor<Questionnaire> {

    String auxiliar;

    int aux;

    Question question = new Question();

    Questionnaire questionnaire = new Questionnaire();

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Questionnaire visitStart(SurveyParser.StartContext ctx) {
        return visit(ctx.questionario());
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Questionnaire visitQuestionario(SurveyParser.QuestionarioContext ctx) {
        visit(ctx.regraIdQuestionario());
        visit(ctx.regraTitulo());
        int size = ctx.regraMensagem().size();
        if (size == 2){
            aux=0;
            visit(ctx.regraMensagem(0));
            aux=1;
            visit(ctx.regraMensagem(1));
        } else {
            aux=1;
            visit(ctx.regraMensagem(0));
        }


        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Questionnaire visitRegraIdQuestionario(SurveyParser.RegraIdQuestionarioContext ctx) {
        StringBuilder id = new StringBuilder();
        int size = ctx.alfanumerico().size();
        for(int i=0; i<=size; i++){
            if(ctx.getChild(i).equals(ctx.HIFEN())){
                id.append(ctx.HIFEN().getText());
            }else {
                visit(ctx.getChild(i));
                id.append(auxiliar);
            }
        }
        questionnaire.modifyId(new Identifier(id.toString()));
        auxiliar = null;
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Questionnaire visitAlfanumerico(SurveyParser.AlfanumericoContext ctx) {
        auxiliar = ctx.getText();
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Questionnaire visitRegraTitulo(SurveyParser.RegraTituloContext ctx) {
        visit(ctx.frase());
        questionnaire.modifyTitle(new Titulo(auxiliar));
        auxiliar = null;
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Questionnaire visitFrase(SurveyParser.FraseContext ctx) {
        auxiliar = ctx.getText();
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Questionnaire visitRegraMensagem(SurveyParser.RegraMensagemContext ctx) {
        StringBuilder message = new StringBuilder();
        int size = ctx.frase().size();
        for (int i= 0; i<size;i++){
            visit(ctx.frase(i));
            message.append(auxiliar);
            visit(ctx.pontucao(i));
            message.append(auxiliar);
            message.append("\n");
        }
        if(aux==0){
            questionnaire.modifyInitialMessage(new Message(message.toString()));
        } else if (aux == 1){
            questionnaire.modifyFinalMessage(new Message(message.toString()));
        }
        return visitChildren(ctx);
    }

    /**
     * {@inheritDoc}
     *
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     */
    @Override public Questionnaire visitPontucao(SurveyParser.PontucaoContext ctx) {
        auxiliar = ctx.getText();
        return null;
    }

}
