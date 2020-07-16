package br.example.app.customer;

import br.example.app.customer.dto.CustomerDTOIn;
import br.example.app.customer.domain.Customer;
import br.example.app.customer.dto.CustomerDTOOut;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public static CustomerDTOOut convertToDtoOut(Customer customer) {
        return new CustomerDTOOut(customer.getId(), customer.getName(), customer.getCpf(), customer.getDateOfBirth(), customer.getAddress());
    }

    private CustomerMapper() {
    }

    public static Customer convertToEntity(CustomerDTOIn customerDTOIn){
        return Customer.customerBuilder()
                .name(customerDTOIn.getName())
                .cpf(customerDTOIn.getCpf())
                .dateOfBirth(customerDTOIn.getDateOfBirth())
                .adress(customerDTOIn.getAddress())
                .build();
    }

    public static Customer searchMap(final CustomerDTOIn customerDTOIn) {
        if (customerDTOIn == null){
            return null;
        }
        Customer.Builder builder = Customer.customerBuilder()
                .name(customerDTOIn.getName())
                .cpf(customerDTOIn.getCpf());

          return builder.build();
    }

     public static List<CustomerDTOOut> convertToListDto(final List<Customer> customer){
            List<CustomerDTOOut> customerDTOOuts = new ArrayList<>();
            for (Customer t: customer) {
                customerDTOOuts.add(convertToDtoOut(t));
            }
            return customerDTOOuts;
     }


}


