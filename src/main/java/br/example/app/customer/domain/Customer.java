package br.example.app.customer.domain;


import javax.persistence.Id;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long Id;
    private String name;
    private String cpf;
    private Date dateOfBirth;

    @OneToOne(cascade = {CascadeType.ALL})
    private Adress adress;

    public Customer() {
    }

    public Customer(Long Id, String name, String cpf, Date dateOfBirth, Adress adress) {
        this.Id = Id;
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
    }

    public Customer(String jose, String s, Date date, Adress adress) {
    }

    public Long getId() {
        return Id;
    }

    public Customer(Builder builder) {
        Id =builder.Id;
        name = builder.name;
        cpf = builder.cpf;
        dateOfBirth = builder.dateOfBirth;
        adress = builder.adress;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public static Builder customerBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private Long Id;
        private String name;
        private String cpf;
        private Date dateOfBirth;
        private Adress adress;

        private Builder() {
        }

        public Builder Id(Long val) {
            Id = val;
            return this;
        }

        public Builder name(String val) {
            name = val;
            return this;
        }

        public Builder cpf(String val) {
            cpf = val;
            return this;
        }

        public Builder dateOfBirth(Date val) {
            dateOfBirth = val;
            return this;
        }

        public Builder adress(Adress val) {
            adress = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

}
