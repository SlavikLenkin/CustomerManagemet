package com.mycompany.service;

import com.mycompany.repository.Customer;
import com.mycompany.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {

    final
    CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAllCustomers() {
        return repository.findAll();
    }

    public Customer findCustomerById(String id) {
        return repository.findCustomerById(id);
    }

    public void delete(Customer customer) {
        repository.delete(customer);
    }

    public Customer save(Customer customer) {
        String id = UUID.randomUUID().toString();
        customer.setId(id);
        customer.setHref("https://host:port/tmf-api/customerManagement/v4/customer/" + id);
        repository.save(customer);
        return customer;
    }

    public Customer update(Customer customer){
        repository.save(customer);
        return customer;
    }
}
