package eapli.base.persistence.impl.jpa;

import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repository.SurveyRepository;

public class JpaSurveyRepository extends BasepaRepositoryBase<Questionnaire, Long, Long>
        implements SurveyRepository {

    JpaSurveyRepository() {
        super("idBaseDeDados");
    }

}
