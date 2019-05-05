package authentication.authenticated;

import domain.accoun.Account;
import repository.AccountRepository;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;


@RequestScoped
public class AuthenticatedUserProducer {

    @Produces
    @RequestScoped
    @AuthenticatedUser
    private Account authenticatedUser;

    @EJB
    AccountRepository personBean;

    public void handleAuthenticationEvent(@Observes @AuthenticatedUser long ID) {
        this.authenticatedUser = findUser(ID);
    }

    private Account findUser(long ID) {
        return personBean.find(ID);
    }
}
