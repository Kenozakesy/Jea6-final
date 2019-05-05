package controller;

import domain.post.Post;
import domain.post.PostDTO;
import repository.PostRepository;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/post")
public class PostController {
    @EJB
    PostRepository postRepository;

    public PostController()
    {

    }

    @GET
    @Path("/ping")
    public String item()
    {
        return "welkom bij posts";
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Post getPost(@PathParam("id") long id)
    {
        return postRepository.find(id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getPosts()
    {
        return postRepository.findAll();
    }

    @GET
    @Path("/channel/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> getPostsByChannel(@PathParam("id") long id)
    {
        return postRepository.getPostByChannelId(id);
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(PostDTO postDTO) {
        Post group = new Post(postDTO);
        postRepository.save(group);
        return Response.ok().entity(group).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(PostDTO postDTO) {
        Post post = new Post(postDTO);
        postRepository.save(post);
        return Response.ok().entity(post).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") long id) {
        postRepository.delete(id);
        return Response.ok(true).build();
    }
}
