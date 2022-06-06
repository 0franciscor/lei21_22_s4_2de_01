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

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long idBaseDeDados;

    private Long questionId;

    private Message Message;

    public void modifyId(Long identifier) {
        this.questionId = identifier;
    }

    public void modifyMessage(Message Message) {
        this.Message = Message;
    }


}
