package Alina.sarafan.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table
@Builder
@EqualsAndHashCode(of = {"id"})
public class Arnd {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private String phone;
    private String samokaty;
    private String coun;
    private String day_start;
    private String day_end;
    private String status;

    public Arnd(Long id, String name, String surname, String patronymic, String phone, String samokaty, String coun, String day_start, String day_end, String status) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.phone = phone;
        this.samokaty = samokaty;
        this.coun = coun;
        this.day_start = day_start;
        this.day_end = day_end;
        this.status = status;
    }

    public Arnd() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSamokaty() {
        return samokaty;
    }

    public void setSamokaty(String samokaty) {
        this.samokaty = samokaty;
    }

    public String getCoun() {
        return coun;
    }

    public void setCoun(String coun) {
        this.coun = coun;
    }

    public String getDay_start() {
        return day_start;
    }

    public void setDay_start(String day_start) {
        this.day_start = day_start;
    }

    public String getDay_end() {
        return day_end;
    }

    public void setDay_end(String day_end) {
        this.day_end = day_end;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
