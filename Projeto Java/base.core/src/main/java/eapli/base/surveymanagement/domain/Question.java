package eapli.base.surveymanagement.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 *
 * Created by Rita Ariana Sobral on 02/06/2022.
 */
@Entity
public class Question implements Serializable {

    public enum Type {
        FREE_TEXT, NUMERIC, SINGLE_CHOICE, SINGLE_CHOICE1,
        MULTIPLE_CHOICE, MULTIPLE_CHOICE1, SORTING_OPTIONS, SCALING_OPTIONS,
    }

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idBaseDeDados;

    private Long questionId;

    @AttributeOverrides({
            @AttributeOverride(name = "message", column = @Column(name = "pergunta"))
    })
    private Message pergunta;

    @AttributeOverrides({
            @AttributeOverride(name = "message", column = @Column(name = "instruction"))
    })
    private Message instruction;

    @AttributeOverrides({
            @AttributeOverride(name = "message", column = @Column(name = "extraInfo"))
    })
    private Message extraInfo;

    private Type type;

    public void modifyId(Long identifier) {
        this.questionId = identifier;
    }

    public void modifyPergunta(Message pergunta) {
        this.pergunta = pergunta;
    }

    public void modifyInstruction(Message instruction) {
        this.instruction = instruction;
    }

    public void modifyExtraInfo(Message extraInfo) {
        this.extraInfo = extraInfo;
    }

    public void modifyType(String type){
        if (type.equals("Free-Text")){
            this.type=Type.FREE_TEXT;
        } else if (type.equals("Numeric")){
            this.type=Type.NUMERIC;
        } else if (type.equals("Single-Choice")){
            this.type=Type.SINGLE_CHOICE;
        } else if (type.equals("Single-Choice with input value")){
            this.type=Type.SINGLE_CHOICE1;
        } else if (type.equals("Multiple-Choice")){
            this.type=Type.MULTIPLE_CHOICE;
        } else if (type.equals("Multiple-Choice with input value")){
            this.type=Type.MULTIPLE_CHOICE1;
        } else if (type.equals("Sorting Options")){
            this.type=Type.SORTING_OPTIONS;
        } else if (type.equals("Scaling Options")){
            this.type=Type.SCALING_OPTIONS;
        }
    }

}
