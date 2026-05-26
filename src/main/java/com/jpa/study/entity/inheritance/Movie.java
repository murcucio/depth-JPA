package main.java.com.jpa.study.entity.inheritance;

import com.jpa.study.entity.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 영화 엔티티 (Item 상속)
 */
@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

    private String director;
    private String actor;

    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }

    public String getActor() { return actor; }
    public void setActor(String actor) { this.actor = actor; }
}
