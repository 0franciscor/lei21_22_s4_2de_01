package eapli.base.surveymanagement.domain;

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
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    private Long sectionId;

    private Titulo titulo;

    private Message Message;

    @OneToMany()
    @JoinColumn(name = "question_section_id")
    private List<Question> questions = new ArrayList<>();


    public Long sectionId(){
        return this.sectionId;
    }

    public void modifyId(Long identifier) {
        this.sectionId = identifier;
    }

    public void modifyTitle(Titulo titulo) {
        this.titulo = titulo;
    }

    public void modifyMessage(Message Message) {
        this.Message = Message;
    }

    public boolean addQuestion(final Question question) {
        return questions.add(question);
    }

}
