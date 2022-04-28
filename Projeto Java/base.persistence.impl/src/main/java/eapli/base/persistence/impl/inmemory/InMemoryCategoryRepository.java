package eapli.base.persistence.impl.inmemory;

import eapli.base.categorymanagement.domain.AlphanumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.domain.Description;
import eapli.base.categorymanagement.domain.SuperCategory;
import eapli.base.categorymanagement.repositories.CategoryRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryCategoryRepository extends InMemoryDomainRepository<Category, Long> implements CategoryRepository {

    static {
        InMemoryInitializer.init();
    }

    @Override
    public Iterable<Category> findByAlphanumericCode(AlphanumericCode alphanumericCode) {
        return null;
    }

    @Override
    public Iterable<Category> findByDescription(Description description) {
        return null;
    }

    @Override
    public Iterable<Category> findBySuperCategory(SuperCategory superCategory) {
        return null;
    }


}
