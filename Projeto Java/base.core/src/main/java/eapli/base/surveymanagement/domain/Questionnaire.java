package eapli.base.surveymanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * Created by Rita Ariana Sobral on 02/06/2022.
 */
@Entity
public class Questionnaire implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idBaseDeDados;

    private Identifier surveyId;

    private Titulo titulo;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "message", column = @Column(name = "initial_message"))
    })
    private Message initialMessage;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "message", column = @Column(name = "final_message"))
    })
    private Message finalMessage;

    private Period period;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JoinColumn(name = "survey_id")
    private List<Section> sections = new ArrayList<>();


    public Questionnaire(){}



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
    public Long identity() {
        return this.idBaseDeDados;
    }

    public void modifyId(Identifier surveyIdentifier) {
        this.surveyId = surveyIdentifier;
    }

    public void modifyTitle(Titulo titulo) {
        this.titulo = titulo;
    }

    public void modifyInitialMessage(Message initialMessage) {
        this.initialMessage = initialMessage;
    }

    public void modifyFinalMessage(Message finalMessage) {
        this.finalMessage = finalMessage;
    }

    public boolean addSection(final Section section) {
        return sections.add(section);
    }

    public void modifyPeriod(final Period period){
        this.period = period;
    }
}
