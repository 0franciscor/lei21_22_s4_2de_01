package eapli.base.persistence.impl.inmemory;

import eapli.base.surveymanagement.domain.Questionnaire;
import eapli.base.surveymanagement.repository.SurveyRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemorySurveyRepository extends InMemoryDomainRepository<Questionnaire, Long>
        implements SurveyRepository {

    static {
        InMemoryInitializer.init();
    }
}
