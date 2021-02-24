package com.mycompany.controller;

import com.mycompany.ApiPath;
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

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customerManagement/v4")
public class CustomerController implements ApiPath {


    final
    FullCustomerService fullCustomerService;



    public CustomerController(FullCustomerService fullCustomerService) {
        this.fullCustomerService = fullCustomerService;
    }



    @GetMapping(PATH_CUSTOMER)
    public List<FullCustomer> getAll() {
        return fullCustomerService.getAllFullCustomer();
    }


    @GetMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<FullCustomer> getCustomerById(@PathVariable(value = ID) String id) {
        FullCustomer fullCustomer = fullCustomerService.getFullCustomerById(id);
        if (fullCustomer == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(fullCustomer);
    }

    @PostMapping(PATH_CUSTOMER)
    public FullCustomer createCustomer(@Valid @RequestBody FullCustomer fullCustomer) {
        return fullCustomerService.save(fullCustomer);
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity deleteCustomer(@PathVariable(value = ID) String id) {
        FullCustomer fullCustomer = fullCustomerService.getFullCustomerById(id);
        if (fullCustomer == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        fullCustomerService.delete(fullCustomer);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
