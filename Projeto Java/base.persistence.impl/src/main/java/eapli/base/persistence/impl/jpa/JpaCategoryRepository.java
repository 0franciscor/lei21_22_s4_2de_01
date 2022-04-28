package eapli.base.persistence.impl.jpa;

import eapli.base.categorymanagement.domain.AlphanumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.domain.Description;
import eapli.base.categorymanagement.domain.SuperCategory;
import eapli.base.categorymanagement.repositories.CategoryRepository;

import java.util.Optional;

public class JpaCategoryRepository extends BasepaRepositoryBase<Category, Long, Long> implements CategoryRepository {

    JpaCategoryRepository() {
        super("categoryId");
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

    @Override
    public <S extends Category> S save(S entity) {
        return null;
    }

    @Override
    public Optional<Category> ofIdentity(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Category entity) {

    }

    @Override
    public void deleteOfIdentity(Long entityId) {

    }
}
