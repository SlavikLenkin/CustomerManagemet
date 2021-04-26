package com.mycompany.controller;

import com.mycompany.ApiPath;
import com.mycompany.kafka.Producer;
import com.mycompany.model.CustomerDto;
import com.mycompany.service.CustomerService;
import com.mycompany.service.EventService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customerManagement/v4")
@Api(tags = "Customer")
@Slf4j
public class CustomerController implements ApiPath {

    private final CustomerService customerService;
    private final Producer producer;
    private final EventService eventService;

    public CustomerController(CustomerService customerService, Producer producer, EventService eventService) {
        this.customerService = customerService;
        this.producer = producer;
        this.eventService = eventService;
    }

    @ApiOperation(value = "getAll")
    @GetMapping(PATH_CUSTOMER)
    public ResponseEntity<List<CustomerDto>> getAll() {
        log.debug("getAll");
        List<CustomerDto> customerDtoList = customerService.findAllCustomers();
        if (customerDtoList == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(customerDtoList);
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
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        log.debug("createCustomer");
        if (customerDto == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        this.producer.sendMessage(eventService.createEvent(customerDto, "CustomerCreateEvent")
                .toString());
        return ResponseEntity.ok().body(customerService.save(customerDto));
    }

    @ApiOperation(value = "patchCustomer")
    @RequestMapping(value = PATH_CUSTOMER_ID, method = RequestMethod.PATCH)
    public ResponseEntity<CustomerDto> patchCustomer(@PathVariable(value = ID) String id,
                                                     @RequestBody CustomerDto customerDtoUpdate) {
        log.debug("patchCustomer {}, {}", id, customerDtoUpdate.toString());
        CustomerDto customerDto = customerService.update(id, customerDtoUpdate);
        if (customerDto == null)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        if (Optional.ofNullable(customerDtoUpdate.getStatus()).isPresent()){
            this.producer.sendMessage(eventService.createEvent(customerDtoUpdate.getStatus()
                    ,"CustomerStateChangeEvent").toString());
        }else {
            this.producer.sendMessage(eventService.createEvent(customerDto, "CustomerAttributeValueChangeEvent")
                    .toString());
        }
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
        this.producer.sendMessage(eventService.createEvent(customerDto, "CustomerDeleteEvent")
                .toString());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
