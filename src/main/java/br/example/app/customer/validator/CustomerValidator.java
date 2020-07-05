package br.example.app.customer.validator;

import br.example.app.customer.CustomerRepository;
import br.example.app.customer.domain.Customer;
import br.example.app.customer.dto.CustomerDTOIn;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;

@Component
public class CustomerValidator implements Validator {

    private CustomerRepository customerRepository;
    private MessageSource messageSource;
    private Predicate<String> StringValidPredicate = e -> e != null && e.length() !=0;

    public CustomerValidator(CustomerRepository customerRepository, MessageSource messageSource) {
        this.customerRepository = customerRepository;
        this.messageSource = messageSource;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return CustomerDTOIn.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CustomerDTOIn customer = (CustomerDTOIn) target;

        List<Customer> customers = customerRepository.findByName(customer.getName());

        if (customerRepository.findByName(customer.getName()).stream().count() > 0) {
            errors.rejectValue("name", "not found");
           // new IllegalArgumentException("Name " + customer.getName() + "invalid.");

        }

        if (!StringValidPredicate.test(customer.getCpf())) {
            errors.rejectValue("cpf", "notfound");
            //new IllegalArgumentException("CPF not valid " + customer.getCpf() + "required and must not be null.");
        }

    }

/*
    private void addFieldError(Errors errors, String field, String errorCode) {
        String defaultMessage = messageSource.getMessage( errorCode, null, errorCode, Locale.ENGLISH);
        errors.rejectValue(field, errorCode);
    }
*/

}
