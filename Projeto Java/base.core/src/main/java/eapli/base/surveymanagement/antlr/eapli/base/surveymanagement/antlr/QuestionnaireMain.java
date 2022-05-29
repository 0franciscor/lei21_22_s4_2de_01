package eapli.base.surveymanagement.antlr.eapli.base.surveymanagement.antlr;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

public class QuestionnaireMain {

    public static void parseWithVisitor(String file) {
        try {
            SurveyLexer lexer = new SurveyLexer(CharStreams.fromFileName(file));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            SurveyParser parser = new SurveyParser(tokens);
            ParseTree tree = parser.start();
            SurveyVisitor eval = new QuestionnaireVisitor();
            eval.visit(tree);
            System.out.println("Survey has a valid format!");
        } catch(IOException e) {
            System.out.println("Make sure the file has the correct path");
        } catch (Exception e) {
            System.out.println("The Survey does not follow the required format.");
        }
    }

    public static void main(String[] args) {
        parseWithVisitor("C:\\Users\\arian\\lei21_22_s4_2de_01\\Projeto Java\\teste.txt");
    }

}