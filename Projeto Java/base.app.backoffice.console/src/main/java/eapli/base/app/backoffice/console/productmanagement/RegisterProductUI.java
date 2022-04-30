package eapli.base.app.backoffice.console.productmanagement;

import eapli.base.app.backoffice.console.categorymanagement.CategoryPrinter;
import eapli.base.categorymanagement.domain.Category;

import eapli.base.productmanagement.application.RegisterProductController;

import eapli.framework.domain.repositories.IntegrityViolationException;
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


        String productionCode = null;

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
        final Double weight = Console.readDouble("Weight:");
        final Double volume = Console.readDouble("Volume:");

        String option = Console.readLine("Do you want to insert the production Code?\n (yes|no)\n");

        if(option.equalsIgnoreCase("yes")){
            productionCode = Console.readLine("Production Code:");
        }

        try {
            this.registerProductController.registerProduct(theCategory,uniqueInternalCode,shortDescription,extendedDescription,technicalDescription,barcode,brandName,reference,productionCode,priceWithoutTaxes,priceWithTaxes,weight,volume);
        } catch (@SuppressWarnings("unused") final IntegrityViolationException e) {
            System.out.println("You tried to enter a product which already exists in the database..");
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register a Product";
    }
}
