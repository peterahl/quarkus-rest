package org.acme.rest.json;


/**
 * PlayerDTO
 */
public class PlayerDTO {

    private String name;
    private Long id;

    public PlayerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public PlayerDTO() {
    }

    public String getName() {
      return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }
}
