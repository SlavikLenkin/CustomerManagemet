package com.mycompany.controller;

import com.mycompany.ApiPath;
import com.mycompany.model.CustomerDto;
import com.mycompany.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/customerManagement/v4")
@Api(tags = "Customer")
@Slf4j
public class CustomerController implements ApiPath {

    final CustomerService customerService;


    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "getAll")
    @GetMapping(PATH_CUSTOMER)
    public List<CustomerDto> getAll() {
        log.debug("getAll");
        return customerService.findAllCustomers();
    }

    @ApiOperation(value = "getCustomerById")
    @GetMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(value = ID) String id) {
        log.debug("getCustomerById {}", id);
        CustomerDto customerDto = customerService.findCustomerById(id);
        if (customerDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(customerDto);
    }

    @ApiOperation(value = "createCustomer")
    @PostMapping(PATH_CUSTOMER)
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        log.debug("createCustomer");
        return customerService.save(customerDto);
    }

    @ApiOperation(value = "patchCustomer")
    @RequestMapping(value = PATH_CUSTOMER_ID, method = RequestMethod.PATCH)
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable(value = ID) String id,
                                                     @RequestBody CustomerDto customerDtoUpdate) {
        log.debug("patchCustomer {}, {}", id, customerDtoUpdate.toString());
        CustomerDto customerDto = customerService.update(id, customerDtoUpdate);
        if (customerDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(customerDto);
    }

    @ApiOperation(value = "deleteCustomer")
    @DeleteMapping(PATH_CUSTOMER_ID)
    public ResponseEntity deleteCustomer(@PathVariable(value = ID) String id) {
        log.debug("deleteCustomer {}", id);
        CustomerDto customerDto = customerService.findCustomerById(id);
        if (customerDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        customerService.delete(customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
