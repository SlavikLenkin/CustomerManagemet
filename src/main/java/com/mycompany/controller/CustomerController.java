package com.mycompany.controller;

import com.mycompany.ApiPath;
import com.mycompany.model.CustomerDto;
import com.mycompany.service.CustomerDtoService;
import com.mycompany.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.runtime.Debug;
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

    final
    CustomerDtoService customerDtoService;

    final
    CustomerService customerService;

    public CustomerController(CustomerDtoService customerDtoService, CustomerService customerService) {
        this.customerDtoService = customerDtoService;
        this.customerService = customerService;
    }

    @ApiOperation(value = "getAll")
    @GetMapping(PATH_CUSTOMER)
    public List<CustomerDto> getAll() {
        log.debug("getAll");
        log.isDebugEnabled();
        return customerDtoService.getAllFullCustomer();
    }

    @ApiOperation(value = "getCustomerById")
    @GetMapping(PATH_CUSTOMER_ID)
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(value = ID) String id) {
        log.debug("getCustomerById {}",id);
        CustomerDto customerDto = customerDtoService.getFullCustomerById(id);
        if (customerDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(customerDto);
    }

    @ApiOperation(value = "createCustomer")
    @PostMapping(PATH_CUSTOMER)
    public CustomerDto createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        log.debug("createCustomer");
        return customerDtoService.save(customerDto);
    }

    @ApiOperation(value = "patchCustomer")
    @RequestMapping(value = PATH_CUSTOMER_ID, method = RequestMethod.PATCH)
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable(value = ID) String id,
                                                     @RequestBody CustomerDto customerDtoUpdate) {
        log.debug("patchCustomer {}, {}", id ,customerDtoUpdate.toString());
        CustomerDto customerDto = customerDtoService.updateFullCustomerById(id, customerDtoUpdate);
        if (customerDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok().body(customerDto);
    }

    @ApiOperation(value = "deleteCustomer")
    @DeleteMapping(PATH_CUSTOMER_ID)
    public ResponseEntity deleteCustomer(@PathVariable(value = ID) String id) {
        log.debug("deleteCustomer {}",id);
        CustomerDto customerDto = customerDtoService.getFullCustomerById(id);
        if (customerDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        customerDtoService.delete(customerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
