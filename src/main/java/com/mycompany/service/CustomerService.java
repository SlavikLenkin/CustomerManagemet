package com.mycompany.service;

import com.mycompany.model.Customer;
import com.mycompany.repository.CustomerRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    final
    CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public List<Customer> findAllCustomer(){
        return repository.findAll();
    }

    public Customer findCustomerById(String id){
        return repository.findByIdCustomer(id);
    }

    public void delete(Customer customer){
        repository.delete(customer);
    }
}
