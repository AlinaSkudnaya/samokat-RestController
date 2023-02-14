package Alina.sarafan.domain;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table
@Builder
@EqualsAndHashCode(of = {"id"})
public class Samokat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name_samokat;
    private String speed;
    private String seat;
    private String color;
    private String price;

    public Samokat(Long id, String name_samokat, String speed, String seat, String color, String price) {
        this.id = id;
        this.name_samokat = name_samokat;
        this.speed = speed;
        this.seat = seat;
        this.color = color;
        this.price = price;
    }

    public Samokat() {

    }

    public Samokat(String name_samokat) {
        this.name_samokat=name_samokat;
    }


    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_samokat() {
        return name_samokat;
    }

    public void setName_samokat(String name_samokat) {
        this.name_samokat = name_samokat;
    }
}