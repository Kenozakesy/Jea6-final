package controller;


import domain.accoun.Account;
import domain.accoun.AccountDTO;
import repository.AccountRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/account")
public class AccountController {

    @EJB
    AccountRepository accountRepository;

    public AccountController()
    {

    }
    @GET
    @Path("/ping")
    public String item()
    {
        return "welkom bij accounts";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account getServer(@PathParam("id") long id)
    {
        return accountRepository.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Account> getServers()
    {
        return accountRepository.findAll();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(AccountDTO accountDTO) {
        Account account = new Account(accountDTO);
        accountRepository.save(account);
        return Response.ok().entity(account).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(AccountDTO accountDTO) {
        Account account = new Account(accountDTO);
        accountRepository.save(account);
        return Response.ok().entity(account).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        accountRepository.delete(id);
        return Response.ok(true).build();
    }
}
