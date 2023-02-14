package Alina.sarafan.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Builder
@EqualsAndHashCode(of = {"id"})
public class Zvk {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String coun;

    public Zvk() {

    }

    public Zvk(String name) {
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getCoun() {
        return coun;
    }

    public void setCoun(String coun) {
        this.coun = coun;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Zvk(Long id, String name, String coun) {
        this.id = id;
        this.name = name;
        this.coun = coun;
    }
}
