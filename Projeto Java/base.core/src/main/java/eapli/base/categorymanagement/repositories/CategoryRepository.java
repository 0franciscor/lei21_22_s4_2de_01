package eapli.base.categorymanagement.repositories;

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

}
