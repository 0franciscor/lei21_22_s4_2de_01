package eapli.base.app.backoffice.console.clientmanagement;

import eapli.base.clientmanagement.application.RegisterClientController;
import eapli.framework.presentation.console.AbstractUI;

/**
 * UI for register a client to the application.
 *
 * Created by Rita Ariana Sobral on 23/04/2022.
 */
public class RegisterClientUI extends AbstractUI {

    private final RegisterClientController registerClientController = new RegisterClientController();


    @Override
    protected boolean doShow() {
        return false;
    }

    @Override
    public String headline() {
        return "Register a Client";
    }
}
