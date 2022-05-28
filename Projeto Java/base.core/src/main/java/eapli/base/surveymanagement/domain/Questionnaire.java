package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;

@Entity
public class Questionnaire implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @Column(name = "questionnaireCode")
    private String code;

    private String title;

    private String initialMessage;

    private String finalMessage;

    @ElementCollection
    @CollectionTable(name = "sections", joinColumns = {@JoinColumn(name = "questionnaireCode", referencedColumnName = "code")})
    @MapKeyColumn(name = "section")
    @Column(name = "question")
    private HashMap<String, String> sectionsAndQuestions = new HashMap<>();

    public Questionnaire(final String code, final String title, final String initialMessage, final HashMap<String,String> sectionsAndQuestions, final String finalMessage){
        this.code=code;
        this.title=title;
        this.initialMessage=initialMessage;
        this.sectionsAndQuestions=sectionsAndQuestions;
        this.finalMessage=finalMessage;
    }

    public Questionnaire(){}

    public String code() {
        return code;
    }

    public String title() {
        return title;
    }

    public String initialMessage() {
        return initialMessage;
    }

    public String finalMessage() {
        return finalMessage;
    }

    public HashMap<String, String> sectionsAndQuestions() {
        return sectionsAndQuestions;
    }

    @Override
    public boolean equals(final Object o){
        return DomainEntities.areEqual(this,o);
    }

    @Override
    public int hashCode(){
        return DomainEntities.hashCode(this);
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Questionnaire)) {
            return false;
        }

        final var that = (Questionnaire) other;
        if (this == that) {
            return true;
        }

        return identity().equals(that.identity());
    }

    @Override
    public String identity() {
        return code;
    }


}
