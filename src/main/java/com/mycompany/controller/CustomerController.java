package com.mycompany.controller;

import com.mycompany.repository.Account;
import com.mycompany.repository.Customer;
import com.mycompany.model.FullCustomer;
import com.mycompany.service.AccountService;
import com.mycompany.service.CustomerService;
import com.mycompany.service.FullCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customerManagement/v4")
public class CustomerController {


    final
    FullCustomerService fullCustomerService;

    public CustomerController(FullCustomerService fullCustomerService) {
        this.fullCustomerService = fullCustomerService;
    }



   /* @GetMapping("/customer")
    public List<Customer> getAll() {
        return customerService.findAllCustomers();
    }
*/

    @GetMapping("/customer/{id}")
    public ResponseEntity<FullCustomer> getCustomerById(@PathVariable(value = "id") String id) {
        FullCustomer fullCustomer = fullCustomerService.getFullCustomerById(id);
        if (fullCustomer == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(fullCustomer);
    }

   /* @PostMapping("/customer")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity deleteCustomer(@PathVariable(value = "id") String id) {
        Customer customer = customerService.findCustomerById(id);
        if (customer == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        customerService.delete(customer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }*/

}
