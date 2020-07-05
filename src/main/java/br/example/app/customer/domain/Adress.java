package br.example.app.customer.domain;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Adress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String street;
    private int numberHome;
    private String City;

    public Adress() {
    }

    public Adress(String street, int numberHome, String city) {
        this.street = street;
        this.numberHome = numberHome;
        this.City = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumberHome() {
        return numberHome;
    }

    public void setNumberHome(int numberHome) {
        this.numberHome = numberHome;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

}