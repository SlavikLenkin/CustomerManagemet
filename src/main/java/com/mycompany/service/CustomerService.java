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
        log.info("findAllCustomers");
        return repository.findAll();
    }

    public Customer findCustomerById(String id) {
        log.info("findCustomerById");
        return repository.findCustomerById(id);
    }

    public void delete(Customer customer) {
        log.info("delete");
        repository.delete(customer);
    }

    public Customer save(Customer customer) {
        log.info("save");
        String id = UUID.randomUUID().toString();
        customer.setId(id);
        customer.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
        repository.save(customer);
        return customer;
    }

    public Customer update(Customer customer) {
        log.info("update");
        repository.updateCustomerById(customer);
        return customer;

    }
}
