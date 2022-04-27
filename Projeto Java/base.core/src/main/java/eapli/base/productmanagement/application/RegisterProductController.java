package eapli.base.productmanagement.application;


import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.repositories.CategoryRepository;

/**
 * Controller responsible for registering a product in the application.
 *
 * Created by Rita Ariana Sobral on 27/04/2022.
 */
public class RegisterProductController {

    private CategoryRepository categoryRepository;

    public Iterable<Category> getCategories() {
        return categoryRepository.findAll();
    }


}
