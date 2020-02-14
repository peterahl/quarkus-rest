package org.acme.rest.json;

import java.util.Set;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/likes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class LikesResource {

    @Inject
    EntityManager em;

    public LikesResource() {
    }

    @POST
    @Path("{gameId}/{playerId}")
    public Response getGame(@PathParam("gameId") long gameId,
            @PathParam("playerId") long playerId) {
        Response resp;
        GameEntity game = em.find(GameEntity.class, gameId);
        PlayerEntity player = em.find(PlayerEntity.class, playerId);
        if (game != null) {
            game.addPlayer(player);
            game.setLikes(game.getPlayers().size());
            em.merge(game);
            final GameDTO gdto = new GameDTO(game.getId(), game.getName(), game.getLikes());
            resp = Response.ok(gdto).build();
        } else {
            resp = Response.noContent().build();
        }
        return resp;
    }

    @DELETE
    @Path("{gameId}/{playerId}")
    public Response deleteGame(@PathParam("gameId") long gameId,
            @PathParam("playerId") long playerId) {
        Response resp;
        GameEntity game = em.find(GameEntity.class, gameId);
        PlayerEntity player = em.find(PlayerEntity.class, playerId);
        if (game != null) {
            Set<PlayerEntity> plset = game.getPlayers();
            plset.remove(player);
            game.setPlayers(plset);
            game.setLikes(game.getPlayers().size());
            em.merge(game);
            final GameDTO gdto = new GameDTO(game.getId(), game.getName(), game.getLikes());
            resp = Response.ok(gdto).build();
        } else {
            resp = Response.noContent().build();
        }
        return resp;
    }
}
