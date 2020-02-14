package org.acme.rest.json;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * PlayerEntity
 */
@Entity
@NamedQuery(name = "Players.findAll", query = "SELECT p FROM PlayerEntity p")
@Table(name = "PLAYERS")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "players")
    private long id;
    private String name;
    @ManyToMany(mappedBy = "players")
    private Set<GameEntity> games = new HashSet<>();

    public PlayerEntity (String name) {
        this.name = name;
    }

    public PlayerEntity() {
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

    public Set<GameEntity> getGames() {
        return this.games;
    }

    public void addGame(GameEntity gameEntity) {
        this.games.add(gameEntity);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof PlayerEntity)) {
            return false;
        }
        PlayerEntity other = (PlayerEntity) obj;
        return Objects.equals(other.id, this.id);
    }
}

