package controller;

import domain.server.Server;
import domain.server.ServerDTO;
import repository.ServerRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/channel")
public class ChannelController {
    @EJB
    ServerRepository serverRepository;

    public ChannelController()
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

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(ServerDTO serverDTO) {
        Server group = new Server(serverDTO);
        serverRepository.save(group);
        return Response.ok().entity(group).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        serverRepository.delete(id);
        return Response.ok(true).build();
    }
}
