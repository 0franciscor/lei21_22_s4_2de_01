package eapli.base.app.user.console.presentation.shopingCarManagement;

import eapli.base.app.user.console.tcp.TcpCliOrder;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class AddProductToShoppingCar extends AbstractUI {
    @Override
    protected boolean doShow() {
        String address = Console.readLine("Insira o ip do seu computador!\n");
        new TcpCliOrder(address);
        return false;
    }

    @Override
    public String headline() {
        return "Add Product to Shopping Car";
    }
}
