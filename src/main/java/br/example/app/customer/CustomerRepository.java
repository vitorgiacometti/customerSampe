package br.example.app.customer;

import br.example.app.customer.domain.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>, PagingAndSortingRepository<Customer, Long> {

    @Transactional(readOnly = true)
    Page<Customer>  findByName(@Param("name") String name, Pageable pageReq);

    @Transactional(readOnly = true)
    List<Customer> findByName(@Param("name") String name);

    @Transactional(readOnly = true)
    Page<Customer> findAll(Specification<Customer> specification, Pageable pageable);
}
