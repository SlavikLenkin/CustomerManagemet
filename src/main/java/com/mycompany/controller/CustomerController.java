package com.mycompany.controller;

import com.mycompany.ApiPath;
import com.mycompany.model.CustomerDto;
import com.mycompany.service.CustomerDtoService;
import com.mycompany.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customerManagement/v4")
public class CustomerController implements ApiPath {


    final
    CustomerDtoService customerDtoService;

    final
    CustomerService customerService;

    public CustomerController(CustomerDtoService customerDtoService, CustomerService customerService) {
        this.customerDtoService = customerDtoService;
        this.customerService = customerService;
    }


    @GetMapping(PATH_CUSTOMER)
    public List<CustomerDto> getAll() {
        return customerDtoService.getAllFullCustomer();
    }


    @GetMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(value = ID) String id) {
        CustomerDto customerDto = customerDtoService.getFullCustomerById(id);
        if (customerDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(customerDto);
    }

    @PostMapping(PATH_CUSTOMER)
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        return customerDtoService.save(customerDto);
    }

   @RequestMapping(value = PATH_CUSTOMER_ID, method = RequestMethod.PATCH)
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable(value = ID) String id,
                                                     @RequestBody CustomerDto customerDtoUpdate){

        CustomerDto customerDto = customerDtoService.updateFullCustomerById(id,customerDtoUpdate);


        return ResponseEntity.ok().body(customerDto);
    }

    @DeleteMapping(PATH_CUSTOMER_ID)
    public ResponseEntity deleteCustomer(@PathVariable(value = ID) String id) {
        CustomerDto customerDto = customerDtoService.getFullCustomerById(id);
        if (customerDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        customerDtoService.delete(customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
