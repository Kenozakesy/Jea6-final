package controller;

import domain.channel.Channel;
import domain.channel.ChannelDTO;
import domain.server.Server;
import domain.server.ServerDTO;
import repository.ChannelRepository;
import repository.PostRepository;
import repository.ServerRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/channel")
public class ChannelController {
    @EJB
    ChannelRepository channelRepository;

    @EJB
    PostRepository postRepository;

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
    public Channel getServer(@PathParam("id") long id)
    {
        return channelRepository.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Channel> getChannels()
    {
        return channelRepository.findAll();
    }

    @GET
    @Path("server/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Channel> getChannelsByServerId(@PathParam("id") long id)
    {
        return channelRepository.findByServerId(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(ChannelDTO channelDTO) {
        Channel channel = new Channel(channelDTO);
        channelRepository.save(channel);
        return Response.ok().entity(channel).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(ChannelDTO channelDTO) {
        Channel channel = new Channel(channelDTO);
        channelRepository.save(channel);
        return Response.ok().entity(channel).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
            //TODO: delete all messages first (does not work correctly)
            postRepository.deleteBychannelId(id); //Post does not delete
            channelRepository.delete(id);

        return Response.ok(true).build();
    }
}
