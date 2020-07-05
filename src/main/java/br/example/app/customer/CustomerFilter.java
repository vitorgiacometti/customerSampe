package br.example.app.customer;

import br.example.app.customer.domain.Customer;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;


public class CustomerFilter {

    static java.util.function.Predicate<String> validatorString = e -> e != null && e.length() !=0;

    public static Specification<Customer> getSpecification(Customer customer){

        return (root, criteriaQuery, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();

            if (validatorString.test(customer.getName())) {
                predicates.add(criteriaBuilder.equal(root.get("name"), customer.getName()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


}
