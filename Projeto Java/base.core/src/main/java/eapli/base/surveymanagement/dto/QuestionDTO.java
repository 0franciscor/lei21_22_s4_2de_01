package eapli.base.surveymanagement.dto;

import eapli.base.surveymanagement.domain.Obligatoriness;
import eapli.base.surveymanagement.domain.Type;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class QuestionDTO implements Serializable {

    public Long questionId;

    public String pergunta;

    public Obligatoriness obligatoriness;

    public String extraInfo;

    public Type type;

    public Long questaoDependente;

    public Long seccaoDependente;

    public Map<Long,String> options = new HashMap<>();

    public QuestionDTO(final Long questionId,final String pergunta,final Obligatoriness obligatoriness,final String extraInfo,final Type type,Map<Long,String> options,Long seccaoDependente,Long questaoDependente){
        this.questionId=questionId;
        this.pergunta=pergunta;
        this.obligatoriness=obligatoriness;
        this.extraInfo=extraInfo;
        this.type=type;
        this.options=options;
        this.questaoDependente=questaoDependente;
        this.seccaoDependente=seccaoDependente;
    }



}
