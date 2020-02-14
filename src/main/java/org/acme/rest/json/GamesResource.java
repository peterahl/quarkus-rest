package org.acme.rest.json;

import java.util.List;
import java.util.stream.Collector;
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

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class GamesResource {

    @Inject
    EntityManager em;

    public GamesResource() {
    }

    @GET
    public Response listGames() {
        final List<GameEntity> games = em.createNamedQuery("Game.findAll", GameEntity.class).getResultList();
        final List<GameDTO> gdto = games
            .stream()
            .map(g -> new GameDTO(g.getId(), g.getName(), g.getLikes()))
            .collect(Collectors.toList());
        return Response.ok(gdto).build();
    }

    @POST
    public Response newGame(GameDTO game) {
        final GameEntity ge = new GameEntity(game.getName());
        em.persist(ge);
        return Response.ok(game).build();
    }

    @GET
    @Path("{id}")
    public Response getGame(@PathParam("id") long id) {
        final Response resp;
        final GameEntity game = em.find(GameEntity.class, id);
        if (game != null) {
            final GameDTO gdto = new GameDTO(game.getId(), game.getName(), game.getLikes());
            resp = Response.ok(gdto).build();
        } else {
            resp = Response.noContent().build();
        }
        return resp;
    }

    @DELETE
    @Path("{id}")
    public Response deleteGame(@PathParam("id") long id) {
        final Response resp;
        final GameEntity game = em.find(GameEntity.class, id);
        if (game != null) {
            em.remove(game);
            resp = Response.ok().build();
        } else {
            resp = Response.noContent().build();
        }
        return resp;
    }
}
