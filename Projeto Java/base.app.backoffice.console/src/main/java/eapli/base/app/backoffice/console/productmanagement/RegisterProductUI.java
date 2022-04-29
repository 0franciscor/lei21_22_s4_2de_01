package eapli.base.app.backoffice.console.productmanagement;

import eapli.base.app.backoffice.console.categorymanagement.CategoryPrinter;
import eapli.base.categorymanagement.domain.Category;

import eapli.base.productmanagement.application.RegisterProductController;

import eapli.framework.io.util.Console;
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

        final String uniqueInternalCode= Console.readLine("Unique Internal Code:");
        final String shortDescription= Console.readLine("Short Description:");
        final String extendedDescription= Console.readLine("Extended Description:");
        final String technicalDescription= Console.readLine("Technical Description:");
        final String barcode = Console.readLine("Barcode");
        final double priceWithoutTaxes= Console.readDouble("Price Without Taxes:");
        final double priceWithTaxes= Console.readDouble("Price With Taxes:");
        final String brandName= Console.readLine("Brand Name:");
        final String reference = Console.readLine("Reference:");
        final double weight = Console.readDouble("Weight:");

        String option = Console.readLine("Do you want to insert the production Code?\n (yes|no)\n");

        if(option.equalsIgnoreCase("yes")){
            String productionCode = Console.readLine("Production Code:");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register a Product";
    }
}
