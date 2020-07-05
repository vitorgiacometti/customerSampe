package br.example.app.customer.dto;

import br.example.app.customer.domain.Adress;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

public class CustomerDTOIn {

    private String name;
    @Size(min = 8, max = 30)
    private String cpf;

    @Past
    private Date dateOfBirth;
    private Adress adress;

    public CustomerDTOIn(String name, String cpf, Date dateOfBirth, Adress adress) {
        this.name = name;
        this.cpf = cpf;
        this.dateOfBirth = dateOfBirth;
        this.adress = adress;
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
}
