package main.java.com.jpa.study.entity.inheritance;

import com.jpa.study.entity.Item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * 음반 엔티티 (Item 상속)
 * @DiscriminatorValue: DTYPE 컬럼에 저장될 구분 값
 */
@Entity
@DiscriminatorValue("A")
public class Album extends Item {

    private String artist;
    private String etc;

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

    public String getEtc() { return etc; }
    public void setEtc(String etc) { this.etc = etc; }
}
