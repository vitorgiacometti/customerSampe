package br.example.app.customer;


import br.example.app.customer.dto.CustomerDTOIn;
import br.example.app.customer.dto.CustomerDTOOut;
import br.example.app.customer.validator.CustomerValidator;
import br.example.app.customer.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;



@RestController
@RequestMapping("/v1/customer")
@Transactional
public class CustomerController {
    private CustomerService customerService;
    private CustomerValidator customerValidator;

    @InitBinder
    public void setupBinder(WebDataBinder binder) {
        binder.addValidators(customerValidator);
    }

    public CustomerController(CustomerService customerService, CustomerValidator customerValidator) {
        this.customerService = customerService;
        this.customerValidator = customerValidator;
    }

    @PostMapping
    public ResponseEntity addCustomer(@Valid @RequestBody CustomerDTOIn customerDtoIn) {
       Customer customer = customerService.saveAndFlush(CustomerMapper.convertToEntity(customerDtoIn));
        return new ResponseEntity<>(CustomerMapper.convertToDtoOut(customer), HttpStatus.CREATED);
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Page<CustomerDTOOut>> getCustomers(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "size", required = false, defaultValue = "10") int size,
            @RequestParam(value = "name", required = false) String name) {
        return new ResponseEntity(customerService.findAllCustomer(page, size, name), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTOOut> findOne(@PathVariable @Min(1) Long id) {
        return new ResponseEntity(customerService.findById(id), HttpStatus.OK) ;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void  deleteCustomer(@PathVariable final int id) {
        customerService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity updateCustomer(@PathVariable final Long id, @RequestBody CustomerDTOIn customerDtoIn){
        Customer customerUpdate = customerService.update(id, CustomerMapper.convertToEntity(customerDtoIn));
        return new ResponseEntity(customerUpdate, HttpStatus.OK);
    }



}
