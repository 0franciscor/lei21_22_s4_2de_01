package eapli.base.categorymanagement.repositories;

<<<<<<< HEAD
import eapli.base.categorymanagement.domain.AlphanumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.domain.Description;
import eapli.base.categorymanagement.domain.SuperCategory;
import eapli.framework.domain.repositories.DomainRepository;

public interface CategoryRepository extends DomainRepository<Long, Category> {

    Iterable<Category> findByAlphanumericCode(AlphanumericCode alphanumericCode);

    Iterable<Category> findByDescription(Description description);

    Iterable<Category> findBySuperCategory(SuperCategory superCategory);
=======
import eapli.base.categorymanagement.domain.Category;
import eapli.base.clientmanagement.domain.Client;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.domain.repositories.LockableDomainRepository;

/**
 * The repository for Categories.
 * Supporting pessimistic locking in this repository
 *
 * Created by Rita Ariana Sobral on 28/04/2022.
 */
public interface CategoryRepository extends DomainRepository<Long, Category>, LockableDomainRepository<Long, Category> {
>>>>>>> 30ada23cc28814c8b55bd685a9695f21e62ed030

}
