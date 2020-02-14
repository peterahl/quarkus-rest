package org.acme.rest.json;

import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/players")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class PlayerResource {

    @Inject
    EntityManager em;

    public PlayerResource() {
    }

    @GET
    public Response listPlayers() {
        final List<PlayerEntity> players =
                em.createNamedQuery("Players.findAll", PlayerEntity.class).getResultList();
        final List<PlayerDTO> pdto = players
            .stream()
            .map(p -> new PlayerDTO(p.getId(), p.getName()))
            .collect(Collectors.toList());
        return Response.ok(pdto).build();
    }

    @POST
    public Response newPlayer(PlayerDTO player) {
        final PlayerEntity pe = new PlayerEntity(player.getName());
        em.persist(pe);
        return Response.ok(player).build();
    }

    @GET
    @Path("{id}")
    public Response getPlayer(@PathParam("id") long id) {
        final Response resp;
        final PlayerEntity player = em.find(PlayerEntity.class, id);
        if (player != null) {
            final PlayerDTO pdto = new PlayerDTO(player.getId(), player.getName());
            resp = Response.ok(pdto).build();
        } else {
            resp = Response.noContent().build();
        }
        return resp;
    }

    @DELETE
    @Path("{id}")
    public Response deletPlayer(@PathParam("id") long id) {
        Response resp;
        PlayerEntity player = em.find(PlayerEntity.class, id);
        if (player != null) {
            em.remove(player);
            resp = Response.ok().build();
        } else {
            resp = Response.noContent().build();
        }
        return resp;
    }
}
