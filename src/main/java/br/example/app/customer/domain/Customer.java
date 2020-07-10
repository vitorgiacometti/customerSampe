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
    private Address address;

    public Customer() {
    }

    public Customer(Long Id, String name, String cpf, Date dateOfBirth, Address address) {
        this.Id = Id;
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
    }

    public Customer(String jose, String s, Date date, Address address) {
    }

    public Long getId() {
        return Id;
    }

    public Customer(Builder builder) {
        Id =builder.Id;
        name = builder.name;
        cpf = builder.cpf;
        dateOfBirth = builder.dateOfBirth;
        address = builder.address;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public static Builder customerBuilder() {
        return new Builder();
    }

    public static final class Builder {

        private Long Id;
        private String name;
        private String cpf;
        private Date dateOfBirth;
        private Address address;

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

        public Builder adress(Address val) {
            address = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }

}
