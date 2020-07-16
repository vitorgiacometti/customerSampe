package br.example.app.customer;


import br.example.app.customer.domain.Customer;
import br.example.app.customer.dto.CustomerDTOOut;
import br.example.app.customer.exception.CustomException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.function.Predicate;


@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private Predicate<String> StringValidPredicate = e -> e != null && e.length() !=0;

    public Page<CustomerDTOOut> findAllCustomer(int page, int size, String name) {
        PageRequest pageRequest = PageRequest.of( page, size,  Sort.Direction.ASC,"name");
        Page<Customer> customerPage = null;
        if(StringValidPredicate.test(name)){
            customerPage = customerRepository.findByName(name, pageRequest);
        }else {
            customerPage = customerRepository.findAll(pageRequest);
        }
        return new PageImpl<>(CustomerMapper.convertToListDto(customerPage.getContent()), pageRequest, customerPage.getTotalElements());
    }

    public Customer update(Long id, Customer customerUpdate){
       return customerRepository.findById(id)
                .map(customer -> {
                    customer.setName(customerUpdate.getName());
                    customer.setCpf(customerUpdate.getCpf());
                    customer.setDateOfBirth(customerUpdate.getDateOfBirth());
                    customer.setAddress(customerUpdate.getAddress());
                    return customerRepository.saveAndFlush(customer);
                })
                .orElseGet(() -> {
                    customerUpdate.setId(id);
                    return customerRepository.saveAndFlush(customerUpdate);
                });
    }

    public void save(Customer customer) { customerRepository.saveAndFlush(customer);  }

    public void delete(long id) {
         customerRepository.deleteById(id);
    }

    public CustomerDTOOut findById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomException(id));
        return CustomerMapper.convertToDtoOut(customer);
    }

    public Customer saveAndFlush(Customer customer) {
       return customerRepository.saveAndFlush(customer);
    }

    public  List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }
}
