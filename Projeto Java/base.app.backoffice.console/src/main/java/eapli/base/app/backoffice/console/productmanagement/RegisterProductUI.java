package eapli.base.app.backoffice.console.productmanagement;

import eapli.base.app.backoffice.console.categorymanagement.CategoryPrinter;
import eapli.base.categorymanagement.domain.Category;

import eapli.base.productmanagement.application.RegisterProductController;

import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;


/**
 * UI for register a product to the application.
 *
 * Created by Rita Ariana Sobral on 27/04/2022.
 */
public class RegisterProductUI extends AbstractUI {

    private final RegisterProductController registerProductController = new RegisterProductController();


    @Override
    protected boolean doShow() {
        final Iterable<Category> categories = this.registerProductController.getCategories();

        final SelectWidget<Category> selector = new SelectWidget<>("Categories:", categories,
                new CategoryPrinter());
        selector.show();
        final Category theCategory = selector.selectedElement();



        return false;
    }

    @Override
    public String headline() {
        return "Register a Product";
    }
}
