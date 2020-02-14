package org.acme.rest.json;


/**
 * GameDTO
 */
public class GameDTO {

    private String name;
    private int likes;
    private long id;

    public GameDTO(long id, String name, int likes) {
        this.name = name;
        this.id = id;
        this.likes = likes;
    }

    public GameDTO() {
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
      return this.likes;
    }


    public void setLikes(int likes) {
      this.likes = likes;
    }

    public long getId() {
      return id;
    }

    public void setId(long id) {
      this.id = id;
    }

}
