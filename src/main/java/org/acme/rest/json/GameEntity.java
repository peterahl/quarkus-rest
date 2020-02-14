package org.acme.rest.json;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * GameEntity
 */
@Entity
@NamedQuery(name = "Game.findAll", query = "SELECT g FROM GameEntity g")
@Table(name = "GAMES")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "games")
    private long id;
    private String name;
    @ManyToMany
    @JoinTable(name="GAMES_PLAYERS",
               joinColumns=@JoinColumn(name="GAME_ID"),
               inverseJoinColumns=@JoinColumn(name="PLAYER_ID"))
    private Set<PlayerEntity> players = new HashSet<>();
    private int likes;

    public GameEntity (String name) {
        this.name = name;
    }

    public GameEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<PlayerEntity> getPlayers(){
        return this.players;
    }

    public void addPlayer(PlayerEntity playerEntity) {
        this.players.add(playerEntity);
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setPlayers(Set<PlayerEntity> players) {
      this.players = players;
    }
}
