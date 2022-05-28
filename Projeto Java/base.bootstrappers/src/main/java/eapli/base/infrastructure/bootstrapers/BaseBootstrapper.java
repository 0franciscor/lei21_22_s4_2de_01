/*
 * Copyright (c) 2013-2019 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package eapli.base.infrastructure.bootstrapers;

import eapli.base.categorymanagement.aplication.CreateCategoryController;
import eapli.base.categorymanagement.domain.AlphanumericCode;
import eapli.base.categorymanagement.domain.Category;
import eapli.base.categorymanagement.domain.Description;
import eapli.base.categorymanagement.domain.SuperCategory;
import eapli.base.clientmanagement.application.RegisterClientController;
import eapli.base.clientmanagement.domain.Client;
import eapli.base.productmanagement.application.RegisterProductController;
import eapli.base.warehouse.application.ImportJsonController;
import eapli.framework.time.util.Calendars;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import eapli.base.infrastructure.persistence.PersistenceContext;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.actions.Action;
import eapli.framework.domain.repositories.ConcurrencyException;
import eapli.framework.domain.repositories.IntegrityViolationException;
import eapli.framework.infrastructure.authz.application.AuthenticationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.SystemUserBuilder;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.strings.util.Strings;
import eapli.framework.validations.Invariants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.*;


/**
 * Base Bootstrapping data app
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings("squid:S106")
public class BaseBootstrapper implements Action {
    private static final Logger LOGGER = LoggerFactory.getLogger(
            BaseBootstrapper.class);

    private static final String POWERUSER_A1 = "poweruserA1";
    private static final String POWERUSER = "poweruser";

    private final AuthorizationService authz = AuthzRegistry.authorizationService();
    private final AuthenticationService authenticationService = AuthzRegistry.authenticationService();
    private final UserRepository userRepository = PersistenceContext.repositories().users();

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = { new MasterUsersBootstrapper(), };

        registerPowerUser();
        authenticateForBootstrapping();
        importFileBootStrap();
        try {
            registerClient();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        registerProduct();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    /**
     * register a power user directly in the persistence layer as we need to
     * circumvent authorisations in the Application Layer
     */
    private boolean registerPowerUser() {
        final SystemUserBuilder userBuilder = UserBuilderHelper.builder();
        userBuilder.withUsername(POWERUSER).withPassword(POWERUSER_A1).withName("joe", "power")
                .withEmail("joe@email.org").withRoles(BaseRoles.POWER_USER);
        final SystemUser newUser = userBuilder.build();

        SystemUser poweruser;
        try {
            poweruser = userRepository.save(newUser);
            assert poweruser != null;
            return true;
        } catch (ConcurrencyException | IntegrityViolationException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            LOGGER.warn("Assuming {} already exists (activate trace log for details)", newUser.username());
            LOGGER.trace("Assuming existing record", e);
            return false;
        }
    }

    private boolean importFileBootStrap(){
        ImportJsonController importJsonController = new ImportJsonController();

        return importJsonController.importWarehouse("warehouse1.json");
    }

    private void registerProduct(){

    }

    private void registerClient() throws ParseException {
        RegisterClientController registerClientController = new RegisterClientController();
        List<List<String>> addresses = new ArrayList<>();
        List<String> address = new ArrayList<>();
        address.add("Rua do Ouro");
        address.add("353");
        address.add("4505-102");
        address.add("Aveiro");
        address.add("Portugal");
        addresses.add(address);
        String strDate = "2006/09/15";
        SimpleDateFormat df = new SimpleDateFormat("yyyy/mm/dd");
        Calendar birthDate = Calendars.fromDate(df.parse(strDate));

        registerClientController.registerClient("Carlota","de Castro Ribeiro e Pereira Sobral","arianasobral26@outlook.pt","+351939214644","PT999999999",addresses,birthDate, Client.Gender.FEMININE);
        registerClientController.registerClient("Manuela","Leite","manuela@isep.ipp.pt","+351999999999","PT999999999",addresses, birthDate, Client.Gender.FEMININE);
        registerClientController.registerClient("Francisco","Redol","francisco@isep.ipp.pt","+351999999989","PT999999999",addresses, birthDate, Client.Gender.MASCULINE);
        registerClientController.registerClient("Pedro","Celestino","pedro@isep.ipp.pt","+351999979999","PT999999999",addresses, birthDate, Client.Gender.MASCULINE);
        registerClientController.registerClient("Rita","Ariana","rita@isep.ipp.pt","+351996999999","PT999999999",addresses, birthDate, Client.Gender.FEMININE);
        registerClientController.registerClient("Diogo","Lourenco","locenzo@isep.ipp.pt","+351997999999","PT999999999",addresses, birthDate, Client.Gender.MASCULINE);
        registerClientController.registerClient("Ines","Cesar","ines@isep.ipp.pt","+351996998999","PT999999999",addresses, birthDate, Client.Gender.FEMININE);
        registerClientController.registerClient("Luis","Busto","busto@isep.ipp.pt","+351996989999","PT999999999",addresses, birthDate, Client.Gender.MASCULINE);
        registerClientController.registerClient("Daniel","Leitao","leitao@isep.ipp.pt","+351956989999","PT999999999",addresses, birthDate, Client.Gender.MASCULINE);
        registerClientController.registerClient("Diogo","Soares","diogo@isep.ipp.pt","+351996989979","PT999999999",addresses, birthDate, Client.Gender.MASCULINE);
        registerClientController.registerClient("Joao Pedro","Xino","joaoPedro@gmail.com","+351996989999","PT999999999",addresses, birthDate, Client.Gender.MASCULINE);
        registerClientController.registerClient("Tiago","Soares","tiago@isep.ipp.pt","+351996989999","PT999999999",addresses, birthDate, Client.Gender.MASCULINE);

    }



    /**
     * authenticate a super user to be able to register new users
     *
     */
    protected void authenticateForBootstrapping() {
        authenticationService.authenticate(POWERUSER, POWERUSER_A1);
        Invariants.ensure(authz.hasSession());
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
