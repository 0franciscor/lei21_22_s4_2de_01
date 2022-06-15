package eapli.base.surveymanagement.dto;

import java.io.Serializable;

public class SurveyDTO implements Serializable {

    private final String id;

    private final String titulo;

    public SurveyDTO(final String id, final String titulo){
        this.id=id;
        this.titulo=titulo;
    }

    @Override
    public String toString() {
        return "------ Questionnaire ------\n" +
                "Identifier=" + id +
                "\nTitle=" + titulo;
    }
}
