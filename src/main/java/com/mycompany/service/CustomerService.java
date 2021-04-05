package com.mycompany.service;

import com.mycompany.repository.Customer;
import com.mycompany.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CustomerService {

    final
    CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;

    }

    public List<Customer> findAllCustomers() {
        log.debug("findAllCustomers");
        return repository.findAll();
    }

    public Customer findCustomerById(String id) {
        log.debug("findCustomerById");
        return repository.findCustomerById(id);
    }

    public void delete(Customer customer) {
        log.debug("delete");
        repository.delete(customer);
    }

    public Customer save(Customer customer) {
        log.debug("save");
        String id = UUID.randomUUID().toString();
        customer.setId(id);
        customer.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
        repository.save(customer);
        return customer;
    }

    public Customer update(Customer customer) {
        log.debug("update");
        repository.updateCustomerById(customer);
        return customer;

    }
}
