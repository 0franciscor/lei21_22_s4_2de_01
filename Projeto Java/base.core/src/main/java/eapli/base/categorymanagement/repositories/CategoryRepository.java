package eapli.base.categorymanagement.repositories;

import eapli.base.categorymanagement.domain.AlphanumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.domain.Description;
import eapli.base.categorymanagement.domain.SuperCategory;
import eapli.framework.domain.repositories.DomainRepository;

public interface CategoryRepository extends DomainRepository<Long, Category> {

    Iterable<Category> findByAlphanumericCode(AlphanumericCode alphanumericCode);

    Iterable<Category> findByDescription(Description description);

    Iterable<Category> findBySuperCategory(SuperCategory superCategory);

}
