package controller;

import domain.accoun.AccountDTO;
import domain.accountServer.AccountServer;
import domain.accountServer.AccountServerDTO;
import domain.server.Server;
import domain.server.ServerDTO;
import org.json.JSONObject;
import repository.AccountServerRepository;
import repository.ServerRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("/server")
public class ServerController {

    @EJB
    ServerRepository serverRepository;

    @EJB
    AccountServerRepository accountServerRepository;

    public ServerController()
    {

    }
    @GET
    @Path("/ping")
    public String item()
    {
        return "welkom bij servers";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Server getServer(@PathParam("id") long id)
    {
        return serverRepository.find(id);
    }

    @GET
    @Path("/user/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Server> getServersByUser(@PathParam("id") long id)
    {
        return serverRepository.findByUser(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Server> getServers()
    {
        return serverRepository.findAll();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ServerDTO serverDTO) {
        Server group = new Server(serverDTO);
        serverRepository.save(group);
        return Response.ok().entity(group).build();
    }

    //HATEOAS in this method
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(ServerDTO serverDTO, @Context UriInfo uriInfo) {

        JSONObject response = new JSONObject();
        Server group = new Server(serverDTO);
        try {
            serverRepository.save(group);

            //koppeling tussen account en server
            AccountServer accountServer = new AccountServer(serverDTO.getUserID(), group.getId());
            accountServerRepository.save(accountServer);

            response.put("Result", group.getName() + " has been added.");
        }catch(Exception e) {
            response.put("Result", group.getName() + " could NOT be added.");
        }

        response.put("_links", getAllLinks(uriInfo));
        return Response.ok(response.toString()).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        serverRepository.delete(id);
        return Response.ok(true).build();
    }

    private Map<String, String> getAllLinks(UriInfo uriInfo) {
        Map<String, String> links = new HashMap<>();

        String base = uriInfo.getBaseUri().toString() + "server/";
        links.put("GET all", base);
        links.put("GET by id", base + "[ID]");
        links.put("POST new server", base);

        return links;
    }
}
