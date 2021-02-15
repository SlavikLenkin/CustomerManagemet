package com.mycompany.controller;

import com.mycompany.exception.ResourceNotFoundException;
import com.mycompany.model.Customer;
import com.mycompany.repository.CustomerRepository;
import com.mycompany.service.CustomerService;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/customerManagement/v4")
public class CustomerController {

    /*final CustomerRepository repository;

    public CustomerController(CustomerRepository repository) {
        this.repository = repository;
    }*/

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public List<Customer> getAll() {
        return customerService.findAllCustomer();
    }


    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(value = "id") String id) {
        Customer customer = customerService.findCustomerById(id);
        return ResponseEntity.ok().body(customer);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/customer/{id}")
    public void deleteCustomer(@PathVariable(value = "id") String id)  {
        Customer customer = customerService.findCustomerById(id);
        customerService.delete(customer);

    }

}
