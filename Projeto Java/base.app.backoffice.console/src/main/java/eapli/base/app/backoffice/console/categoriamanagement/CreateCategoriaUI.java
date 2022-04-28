package eapli.base.app.backoffice.console.categoriamanagement;

import eapli.base.categorymanagement.aplication.CreateCategoryController;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class CreateCategoriaUI extends AbstractUI {

    private final CreateCategoryController createCategoryController = new CreateCategoryController();

    @Override
    protected boolean doShow() {

        final String alphaNumericCode = Console.readLine("Alphanumeric code describing the category - ");
        final String description = Console.readLine("Description that characterizes a category - ");
        final String superCategory = Console.readLine("Super category to which the category belongs - ");

        try {
            this.createCategoryController.createCategory(alphaNumericCode, description, superCategory);

        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a category that already exists in the system.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "-----* Create a Category *-----";
    }
}
